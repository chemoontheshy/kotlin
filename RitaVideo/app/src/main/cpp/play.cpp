//
// Created by Administrator on 2021/3/3.
//

#include <unistd.h>
#include "playheader.h"

void free();

void initLib() {
    // 进程锁
//    pthread_mutex_t *mutex = nullptr;
//    pthread_mutex_lock(mutex);
    static bool isInit = false;
    if (!isInit) {
        avformat_network_init();
        isInit = true;
        unsigned version = avcodec_version();
        LOGE("version is %d", version);
    }
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_qchemmo_ritavideo_ui_fragment_PlayFragment_ffmpeg(
        JNIEnv *env,
        jobject /* this */) {
    initLib();
    return env->NewStringUTF(swresample_configuration());

}

void free() {
    if(img_convert_ctx != nullptr){
        sws_freeContext(img_convert_ctx);
        img_convert_ctx = nullptr;
    }

    if(packet != nullptr){
        av_packet_unref(packet);
        packet = nullptr;
    }
    if(pFrame != nullptr){
        av_frame_free(&pFrame);
        pFrame = nullptr;
    }
    if(pFrameRGB != nullptr){
        av_frame_free(&pFrameRGB);
        pFrameRGB = nullptr;
    }
    if(videoCodec != nullptr ){
        avcodec_close(videoCodec);
        videoCodec =nullptr;
    }
    if(audioCodec != nullptr){
        avcodec_close(audioCodec);
        audioCodec = nullptr;
    }
    if(pFormatCtx != nullptr){
        avformat_close_input(&pFormatCtx);
        pFormatCtx = nullptr;
    }
    av_dict_free(&avdic);
    LOGE("ffmpeg free and close ok");


}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_qchemmo_ritavideo_ui_fragment_PlayFragment_putUrl(JNIEnv *env,
        jobject thiz,
        jstring video_url){
    path = env->GetStringUTFChars(video_url, nullptr);
    initLib();
    //初始化数据
    stopped = false;
    isPlay = false;

    frameFinish = false;
    videoWidth =  0;
    videoHeight = 0;
    oldWidth = 0;
    oldHeight =0;
    countFrame = 0;
    videoStreamIndex = -1;
    audioStreamIndex = -1;

    out_buffer =nullptr;
    packet = nullptr;
    pFrame = nullptr;
    pFrameRGB = nullptr;
    pFormatCtx = nullptr;
    videoCodec = nullptr;
    audioCodec = nullptr;
    img_convert_ctx = nullptr;
    avdic = nullptr;
    videoDecoder = nullptr;
    audioDecoder = nullptr;

    /*
     * 设置参数
     */
    //在打开码流前指定各种参数比如:探测时间/超时时间/最大延时等
    //设置缓存大小,1080p可将值调大
    av_dict_set(&avdic, "buffer_size", "8192000", 0);
    //以tcp方式打开,如果以udp方式打开将tcp替换为udp
    av_dict_set(&avdic, "rtsp_transport", "tcp", 0);
    //设置超时断开连接时间,单位微秒,3000000表示3秒
    av_dict_set(&avdic, "stimeout", "3000000", 0);
    //设置最大时延,单位微秒,1000000表示1秒
    av_dict_set(&avdic, "max_delay", "1000000", 0);
    //自动开启线程数
    av_dict_set(&avdic, "threads", "auto", 0);

    /*
     * 打开视频
     */
    pFormatCtx = avformat_alloc_context();
    int result = avformat_open_input(&pFormatCtx,path, nullptr,&avdic);
    if(result<0){
        LOGE("error,can't open the url");
        return env->NewStringUTF("error-1");
    }
    //释放设置参数
    if(avdic !=nullptr){
        av_dict_free(&avdic);
    }
    //获取流信息
    result = avformat_find_stream_info(pFormatCtx, nullptr);
    if(result<0){
        LOGE("find video stream index error");
        return env->NewStringUTF("error-2");
    }

    //视频流部分开始，打个标记方便折叠代码
    if(true){
        videoStreamIndex = av_find_best_stream(pFormatCtx,AVMEDIA_TYPE_VIDEO,-1,-1,&videoDecoder,0);
        if(videoStreamIndex<0){
            LOGE("find video stream index error");
            return env->NewStringUTF("error-3");
        }
        //获取视频流
        AVStream *videoStream = pFormatCtx->streams[videoStreamIndex];

        //获取视频解码器，或者指定解码器
        videoCodec = avcodec_alloc_context3(nullptr);
        avcodec_parameters_to_context(videoCodec,videoStream->codecpar);

        videoDecoder = avcodec_find_decoder(videoCodec->codec_id);

        if(videoDecoder == nullptr){
            LOGE("video decoder not found");
            return env->NewStringUTF("error-4");
        }

        //设置加速解码
        videoCodec->lowres = videoDecoder->max_lowres;
        videoCodec->flags2 |= AV_CODEC_FLAG2_FAST;

        //打开视频解码器
        result = avcodec_open2(videoCodec,videoDecoder, nullptr);
        if(result<0){
            LOGE("open video codec error");
            return env->NewStringUTF("error-5");
        }

        //获取分辨率大小
        videoHeight = videoCodec->height;
        videoWidth = videoCodec->width;

        //如果没有获取宽高则返回
        if(videoWidth==0 || videoHeight == 0){
            LOGE("find width height error");
            return env->NewStringUTF("error-6");
        }
        LOGE("video stream information -> index:%d",videoStreamIndex);
        LOGE("decodec:%s",videoDecoder->name);
        LOGE("format:%s",pFormatCtx->iformat->name);
        LOGE("time:%ld",(pFormatCtx->duration)/1000000);
        LOGE("resolution:%d",videoHeight*videoWidth);

    }
    //预分配好内存
    packet = av_packet_alloc();
    pFrame = av_frame_alloc();
    pFrameRGB = av_frame_alloc();

    //比较上一次文件的宽度高度，当改变时，需要重新分配内存
    if(oldWidth !=videoWidth || oldHeight != videoHeight){
        int byte = av_image_get_buffer_size(AV_PIX_FMT_RGB32,videoWidth,videoHeight,1);
        out_buffer = (uint8_t *)av_malloc(byte * sizeof(uint8_t));
        oldWidth = videoWidth;
        oldHeight = videoHeight;
    }
    LOGE("videoWidth: %d,videoHeight:%d",videoWidth,videoHeight);
    //定义像素格式,默认格式
    //AVPixelFormat srcFormat = AV_PIX_FMT_YUV420P;
    AVPixelFormat srcFormat;
    AVPixelFormat dstFormat = AV_PIX_FMT_RGB32;

    //通过解码器获取解码格式
    srcFormat = videoCodec->pix_fmt;

    //默认最快速度解码采用SWS_FAST_BILINEAR参数，可能会丢失部分图片数据，可以自行更改成其他参数
    int flags = SWS_FAST_BILINEAR;

    //开辟缓存存储一帧数据
    av_image_fill_arrays(pFrameRGB->data,pFrameRGB->linesize,out_buffer,dstFormat,videoWidth,videoHeight,1);

    //图像转换
    img_convert_ctx = sws_getContext(videoWidth,videoHeight,srcFormat,
            videoWidth,videoHeight,dstFormat,
            flags,nullptr, nullptr, nullptr);
    LOGE("init ffmpeg finish");
    while (av_read_frame(pFormatCtx,packet)>=0){
        int index = packet->stream_index;
        if(index == videoStreamIndex){
            frameFinish = avcodec_send_packet(videoCodec,packet);
            if(frameFinish<0){
                continue;
            }
            frameFinish = avcodec_receive_frame(videoCodec,pFrame);
            if(frameFinish<0){
                continue;
            }
            if(frameFinish>=0){
                sws_scale(img_convert_ctx,
                        (const uint8_t *const *)pFrame->data,
                        pFrame->linesize,
                        0,
                        videoHeight,
                        pFrameRGB->data,
                        pFrameRGB->linesize);
                countFrame++;
                LOGE("finish decode %d frame",countFrame);
            }
        }
        av_packet_unref(packet);
        av_free(packet);
    }

    //线程结束后释放资源
    free();

    return env->NewStringUTF("ffmpeg finish decode");
}
extern "C"
JNIEXPORT void JNICALL
Java_com_qchemmo_ritavideo_view_PlayView_nativeStart(JNIEnv *env, jobject thiz, jstring getPath,
                                                       jobject surface) {
    path = env->GetStringUTFChars(getPath, nullptr);
    LOGE("path:%s",path);
    initLib();
    ANativeWindow *aNativeWindow = ANativeWindow_fromSurface(env,surface);
    if(aNativeWindow== nullptr){
        LOGE("Couldn't get native window from surface.\n");
        return;
    }
    //初始化数据
    stopped = false;
    isPlay = false;

    frameFinish = false;
    videoWidth =  0;
    videoHeight = 0;
    oldWidth = 0;
    oldHeight =0;
    countFrame = 0;
    videoStreamIndex = -1;
    audioStreamIndex = -1;

    out_buffer =nullptr;
    packet = nullptr;
    pFrame = nullptr;
    pFrameRGB = nullptr;
    pFormatCtx = nullptr;
    videoCodec = nullptr;
    audioCodec = nullptr;
    img_convert_ctx = nullptr;
    avdic = nullptr;
    videoDecoder = nullptr;
    audioDecoder = nullptr;

    /*
     * 设置参数
     */
    //在打开码流前指定各种参数比如:探测时间/超时时间/最大延时等
    //设置缓存大小,1080p可将值调大
    av_dict_set(&avdic, "buffer_size", "8192000", 0);
    //以tcp方式打开,如果以udp方式打开将tcp替换为udp
    av_dict_set(&avdic, "rtsp_transport", "tcp", 0);
    //设置超时断开连接时间,单位微秒,3000000表示3秒
    av_dict_set(&avdic, "stimeout", "3000000", 0);
    //设置最大时延,单位微秒,1000000表示1秒
    av_dict_set(&avdic, "max_delay", "1000000", 0);
    //自动开启线程数
    av_dict_set(&avdic, "threads", "auto", 0);

    /*
     * 打开视频
     */
    pFormatCtx = avformat_alloc_context();
    int result = avformat_open_input(&pFormatCtx,path, nullptr,&avdic);
    if(result<0){
        LOGE("error,can't open the url");
        return;
    }
    //释放设置参数
    if(avdic !=nullptr){
        av_dict_free(&avdic);
    }
    //获取流信息
    result = avformat_find_stream_info(pFormatCtx, nullptr);
    if(result<0){
        LOGE("find video stream index error");
        return;
    }

    //视频流部分开始，打个标记方便折叠代码
    if(true){
        videoStreamIndex = av_find_best_stream(pFormatCtx,AVMEDIA_TYPE_VIDEO,-1,-1,&videoDecoder,0);
        if(videoStreamIndex<0){
            LOGE("find video stream index error");
        }
        //获取视频流
        AVStream *videoStream = pFormatCtx->streams[videoStreamIndex];

        //获取视频解码器，或者指定解码器
        videoCodec = avcodec_alloc_context3(nullptr);
        avcodec_parameters_to_context(videoCodec,videoStream->codecpar);

        videoDecoder = avcodec_find_decoder(videoCodec->codec_id);

        if(videoDecoder == nullptr){
            LOGE("video decoder not found");
            return;
        }

        //设置加速解码
        videoCodec->lowres = videoDecoder->max_lowres;
        videoCodec->flags2 |= AV_CODEC_FLAG2_FAST;

        //打开视频解码器
        result = avcodec_open2(videoCodec,videoDecoder, nullptr);
        if(result<0){
            LOGE("open video codec error");
            return;
        }

        //获取分辨率大小
        videoHeight = videoCodec->height;
        videoWidth = videoCodec->width;

        //如果没有获取宽高则返回
        if(videoWidth==0 || videoHeight == 0){
            LOGE("find width height error");
        }
        LOGE("video stream information -> index:%d",videoStreamIndex);
        LOGE("decodec:%s",videoDecoder->name);
        LOGE("format:%s",pFormatCtx->iformat->name);
        LOGE("time:%ld",(pFormatCtx->duration)/1000000);
        LOGE("resolution:%d",videoHeight*videoWidth);

    }
    //预分配好内存
    packet = av_packet_alloc();
    pFrame = av_frame_alloc();
    pFrameRGB = av_frame_alloc();

    //比较上一次文件的宽度高度，当改变时，需要重新分配内存
    if(oldWidth !=videoWidth || oldHeight != videoHeight){
        int byte = av_image_get_buffer_size(AV_PIX_FMT_RGB32,videoWidth,videoHeight,1);
        out_buffer = (uint8_t *)av_malloc(byte * sizeof(uint8_t));
        oldWidth = videoWidth;
        oldHeight = videoHeight;
    }
    //定义像素格式,默认格式
    //AVPixelFormat srcFormat = AV_PIX_FMT_YUV420P;
    AVPixelFormat srcFormat;
    AVPixelFormat dstFormat = AV_PIX_FMT_RGBA;

    //通过解码器获取解码格式
    srcFormat = videoCodec->pix_fmt;

    //默认最快速度解码采用SWS_FAST_BILINEAR参数，可能会丢失部分图片数据，可以自行更改成其他参数
    int flags = SWS_FAST_BILINEAR;

    //开辟缓存存储一帧数据
    av_image_fill_arrays(pFrameRGB->data,pFrameRGB->linesize,
            out_buffer,dstFormat,videoWidth,videoHeight,1);

    //图像转换
    img_convert_ctx = sws_getContext(videoWidth,videoHeight,srcFormat,
                                     videoWidth,videoHeight,dstFormat,
                                     flags,nullptr, nullptr, nullptr);
    LOGE("init ffmpeg finish");
    int ret= ANativeWindow_setBuffersGeometry(aNativeWindow,videoWidth,videoHeight,
            WINDOW_FORMAT_RGBA_8888);
    if(ret<0){
        LOGE("Couldn't set buffers geometry.\n");
        ANativeWindow_release(aNativeWindow);
        return;
    }
    ANativeWindow_Buffer aNativeWindowBuffer;
    while (av_read_frame(pFormatCtx,packet)>=0){
        int index = packet->stream_index;
        if(index == videoStreamIndex){
            frameFinish = avcodec_send_packet(videoCodec,packet);
            if(frameFinish<0){
                continue;
            }
            frameFinish = avcodec_receive_frame(videoCodec,pFrame);
            if(frameFinish<0){
                continue;
            }
            if(frameFinish>=0){
                sws_scale(img_convert_ctx,
                          (const uint8_t *const *)pFrame->data,
                          pFrame->linesize,
                          0,
                          videoHeight,
                          pFrameRGB->data,
                          pFrameRGB->linesize);
                //锁住
                if(ANativeWindow_lock(aNativeWindow,&aNativeWindowBuffer, nullptr)<0){
                    LOGE("cannot lock window");
                    return;
                } else{
                    uint8_t *dst = (uint8_t *)aNativeWindowBuffer.bits;
                    for (int h=0;h<videoHeight;h++){
                        memcpy(dst+h*aNativeWindowBuffer.stride*4,
                                out_buffer+h*pFrameRGB->linesize[0],
                                pFrameRGB->linesize[0]);
                    }
                    ANativeWindow_unlockAndPost(aNativeWindow);
                }
                countFrame++;
                usleep(16000);
                LOGE("finish decode %d frame",countFrame);
            }
        }
        av_packet_unref(packet);
        av_free(packet);
    }

    //线程结束后释放资源
    free();

}