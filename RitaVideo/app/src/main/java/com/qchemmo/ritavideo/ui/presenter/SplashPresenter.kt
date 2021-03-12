package com.qchemmo.ritavideo.ui.presenter

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.mvp.presenter.BasePresenter
import com.qchemmo.ritavideo.ui.activity.MainActivity
import com.qchemmo.ritavideo.ui.view.SplashView

/**
 * @ClassName: SplashPresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/12-16:39
 */
class SplashPresenter: BasePresenter<SplashView>(), ViewPropertyAnimatorListener {
    fun animate(view: View){
        ViewCompat.animate(view).scaleX(1.5f).scaleY(1.5f).setListener(this).duration = 2000
    }

    override fun onAnimationEnd(view: View?) {
        getBaseView()?.setData("test")
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }


}