package com.qchemmo.ritavideo.ui.fragment

import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.MediaController
import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseFragment
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

    override fun init() {

    }
}
