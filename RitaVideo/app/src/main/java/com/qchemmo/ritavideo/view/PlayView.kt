package com.qchemmo.ritavideo.view

import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * @ClassName: PlayView
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/15-15:39
 */
class PlayView:SurfaceHolder.Callback {

    private var surfaceHolder : SurfaceHolder? = null

    fun setSurfaceView(surfaceView: SurfaceView){
        surfaceHolder?.removeCallback(this)
        surfaceHolder = surfaceView.holder
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        surfaceHolder = p0
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
    }
}