package com.qchemmo.toutiao.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.qchemmo.toutiao.R
import com.qchemmo.toutiao.base.BaseActivity
import com.qchemmo.toutiao.ui.main.presenter.MainPresenter
import com.qchemmo.toutiao.ui.main.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainView,MainPresenter>(),MainView {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        test_id.setOnClickListener {
            getPresenter()?.getTest("test")
        }
    }
    override fun initData() {
   }



    override fun createPresenter()=MainPresenter()


    override fun <T> setData(data: T) {
        Log.e("test","===============>$data")
    }

    override fun setError(err: String) {
    }

}
