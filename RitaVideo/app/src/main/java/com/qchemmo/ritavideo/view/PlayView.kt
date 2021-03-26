package com.qchemmo.ritavideo.view

import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.concurrent.thread

/**
 * @ClassName: PlayView
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/15-15:39
 */
class PlayView:SurfaceHolder.Callback{
    companion object {
        init {
            System.loadLibrary("play")
        }
    }

    private var surfaceHolder : SurfaceHolder? = null

    fun setSurfaceView(surfaceView: SurfaceView){
        surfaceHolder = surfaceView.holder
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        surfaceHolder = p0
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
    }

    fun start(path: String) {
        nativeStart(path,surfaceHolder!!.surface)
    }
    private external fun nativeStart(getPath:String, surface: Surface)
}