package com.qchemmo.ktplayer.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.qchemmo.ktplayer.R
import com.qchemmo.ktplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity


class SplashActivity: BaseActivity(), ViewPropertyAnimatorListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 2000
    }

    override fun onAnimationEnd(view: View?) {
        //进入主界面
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }


}