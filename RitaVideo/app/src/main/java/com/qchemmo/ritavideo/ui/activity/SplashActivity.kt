package com.qchemmo.ritavideo.ui.activity
import android.content.Intent
import android.util.Log
import com.qchemmo.ritavideo.Permission.PermissionUtils
import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseActivity
import com.qchemmo.ritavideo.ui.presenter.SplashPresenter
import com.qchemmo.ritavideo.ui.view.SplashView
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @ClassName: SplashAcitivity
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/12-16:29
 */
class SplashActivity :BaseActivity<SplashView,SplashPresenter>(),SplashView {
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
    }

    override fun initData() {
        getPresenter()?.animate(imageView)

    }

    override fun createPresenter(): SplashPresenter? = SplashPresenter()

    override fun <T> setData(data: T) {
        PermissionUtils.readAndWrite(this) {
            val intent : Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    override fun setError(err: String) {
    }
}