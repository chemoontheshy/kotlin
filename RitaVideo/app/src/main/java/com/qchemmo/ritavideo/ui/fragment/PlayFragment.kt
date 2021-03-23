package com.qchemmo.ritavideo.ui.fragment
import android.view.View
import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseFragment
import com.qchemmo.ritavideo.view.PlayView
import kotlinx.android.synthetic.main.fragment_play.*


/**
 * @ClassName: PlayFragment
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/13-16:40
 */
class PlayFragment:BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_play,null)
    }

    override fun init() {
        val playView:PlayView? =null
        playView?.setSurfaceView(sfv_video)
    }

    override fun initListener() {
        btn_play.setOnClickListener{
            tv_test.text = ffmpeg()
        }
    }

    external fun test():String
    external fun ffmpeg():String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("play")
        }
    }
}
