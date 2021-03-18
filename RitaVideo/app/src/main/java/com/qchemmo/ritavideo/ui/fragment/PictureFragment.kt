package com.qchemmo.ritavideo.ui.fragment

import android.content.Context
import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_picture.*
import java.io.FileInputStream

/**
 * @ClassName: PlayFragment
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/13-16:40
 */
class PictureFragment:BaseFragment() {

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_picture,null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun init() {
        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        val uri:Uri = parse(Environment.getExternalStoragePublicDirectory(".kotlin/test.mp4").path)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

    }
}
