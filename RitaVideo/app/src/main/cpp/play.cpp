//
// Created by Administrator on 2021/3/3.
//

#include "playheader.h"


void initLib() {
    // 进程锁
    pthread_mutex_t *mutex = nullptr;
    pthread_mutex_lock(mutex);
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

extern "C"
JNIEXPORT jstring JNICALL
Java_com_qchemmo_ritavideo_ui_fragment_PlayFragment_putUrl(JNIEnv *env,
        jobject thiz,
        jstring video_url){
    path = env->GetStringUTFChars(video_url, 0);
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
        LOG
    }


    return env->NewStringUTF(path);
}