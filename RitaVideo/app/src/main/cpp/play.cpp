//
// Created by Administrator on 2021/3/3.
//
#include <jni.h>

extern "C"
{
#include "include/libavcodec/avcodec.h"
#include "include/libavfilter/avfilter.h"
#include "include/libavformat/avformat.h"
#include "include/libavutil/avutil.h"
#include "include/libswresample/swresample.h"
#include "include/libswscale/swscale.h"
}
extern "C" JNIEXPORT jstring JNICALL
Java_com_qchemmo_ritavideo_ui_fragment_PlayFragment_test(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(swresample_configuration());
}