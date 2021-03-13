package com.qchemmo.ritavideo.ui.activity

import android.content.Intent
import android.widget.Toolbar
import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseActivity
import com.qchemmo.ritavideo.ui.presenter.MainPresenter
import com.qchemmo.ritavideo.ui.view.MainView
import com.qchemmo.ritavideo.utils.ToolBarManager
import kotlinx.android.synthetic.main.activity_toolbar.*
import java.nio.file.Files.find


class MainActivity : BaseActivity<MainView,MainPresenter>(),MainView{

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {

    }

    override fun initData() {
        getPresenter()?.setToolBarTitle("KTPlayer")
    }

    override fun createPresenter(): MainPresenter? =MainPresenter()

    override fun <T> setData(data: T) {
         toolbar.title = data.toString()

    }

    override fun setError(err: String) {
    }

}
