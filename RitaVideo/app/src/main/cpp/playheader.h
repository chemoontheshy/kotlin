//
// Created by Administrator on 2021/3/24.
//

#ifndef RITAVIDEO_PLAYHEADER_H
#define RITAVIDEO_PLAYHEADER_H

#define TAG "C++" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型

#include <jni.h>
#include <iostream>
#include <android/log.h>
#include <android/native_window_jni.h>

using namespace std;
extern "C"
{
#include "include/libavcodec/avcodec.h"
#include "include/libavfilter/avfilter.h"
#include "include/libavformat/avformat.h"
#include "include/libavutil/avutil.h"
#include "include/libavutil/imgutils.h"
#include "include/libswresample/swresample.h"
#include "include/libswscale/swscale.h"
}


volatile bool stopped;
volatile bool isPlay;
int frameFinish; //一帧完成
int videoWidth;  //视频宽度
int videoHeight; //视频高度
int oldWidth;    //上次视频宽度
int oldHeight;   //上次视频高度
int countFrame;
int videoStreamIndex; //视频流索引
int audioStreamIndex; //音频流索引


char *url;            //视频流地址
const char *path;

uint8_t *out_buffer; //存储解码后照片的buffer
AVPacket *packet;    //包对象
AVFrame *pFrame;    //输入帧对象
AVFrame *pFrameRGB; //输出帧对象
AVFormatContext *pFormatCtx; //格式上下文
AVCodecContext *videoCodec; //视频解码器
AVCodecContext *audioCodec; //音频解码器
SwsContext *img_convert_ctx; //处理图片数据对象

AVDictionary *avdic;      //参数对象
AVCodec *videoDecoder;   //视频解码
AVCodec *audioDecoder;   //音频解码

#endif //RITAVIDEO_PLAYHEADER_H
