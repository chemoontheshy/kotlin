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
Java_com_qchemmo_ffplay_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(avfilter_configuration());
}
