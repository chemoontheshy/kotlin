package com.qchemmo.ritavideo.ui.activity

import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseActivity
import com.qchemmo.ritavideo.ui.presenter.MainPresenter
import com.qchemmo.ritavideo.ui.view.MainView


class MainActivity : BaseActivity<MainView,MainPresenter>(),MainView{
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
    }

    override fun initData() {
    }

    override fun createPresenter(): MainPresenter? =MainPresenter()

    override fun <T> setData(data: T) {
    }

    override fun setError(err: String) {
    }

}
