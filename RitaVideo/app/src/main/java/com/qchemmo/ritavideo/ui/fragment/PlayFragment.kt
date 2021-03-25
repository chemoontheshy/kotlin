package com.qchemmo.ritavideo.ui.fragment
import android.os.Environment
import android.util.Log
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

    }

    override fun initListener() {
        val playView = PlayView()
        playView.setSurfaceView(sfv_video)
        val url = Environment.getExternalStoragePublicDirectory("/kotlin/test.mp4")
        btn_play.setOnClickListener{
            playView.start(url.absolutePath);
            Log.e("test","T");
        }
    }

    external fun ffmpeg():String
    private external fun putUrl(video_url:String):String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("play")
        }
    }
}
