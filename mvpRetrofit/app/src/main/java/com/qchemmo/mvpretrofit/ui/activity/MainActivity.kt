package com.qchemmo.mvpretrofit.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qchemmo.mvpretrofit.R
import com.qchemmo.mvpretrofit.base.BaseActivity
import com.qchemmo.mvpretrofit.ui.presenter.MainPresenter
import com.qchemmo.mvpretrofit.ui.view.MainView
import kotlinx.android.synthetic.main.activity_main.*


/**
 * @ClassName: MainActivity
 * @Description: MainActivity
 * @Author: chemoontheshy
 * @Date: 2021/3/11-13:41
 */
class MainActivity :BaseActivity<MainView,MainPresenter>(),MainView {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    /**
     * 监听按钮的变化，执行setText方法
     * */
    override fun init() {
        btn_test.setOnClickListener {
            getPresetter()?.setText()
        }
    }

    override fun initData() {
    }

    /**
     * 传入mainPresenter
     * */
    override fun createPresenter(): MainPresenter? = MainPresenter()

    /**
     * presenter通知View，更新UI
     * */
    override fun <T> setData(data: T) {
        tv_test.text = data.toString()
    }

    override fun setError(err: String) {
    }


}
