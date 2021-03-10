package com.qchemmo.mvpdemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.qchemmo.mvpdemo.R
import com.qchemmo.mvpdemo.base.BaseActivity
import com.qchemmo.mvpdemo.http.HttpUtils
import com.qchemmo.mvpdemo.ui.presenter.MainPresenter
import com.qchemmo.mvpdemo.ui.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainView,MainPresenter>(),MainView {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        test.setOnClickListener {
            getPresenter()?.getWeather()

        }
   }

    override fun initData() {
    }

    override fun createPresenter()= MainPresenter()
    override fun <T> setData(data: T) {
        test.text = data.toString()
        Log.e("test","===============>$data")
   }

    override fun setError(err: String) {
    }
    override fun onDestroy() {
        super.onDestroy()
    }

}
