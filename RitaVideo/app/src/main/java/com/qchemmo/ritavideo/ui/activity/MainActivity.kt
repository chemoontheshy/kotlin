package com.qchemmo.ritavideo.ui.activity

import android.content.Intent
import android.widget.Toolbar
import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseActivity
import com.qchemmo.ritavideo.ui.presenter.MainPresenter
import com.qchemmo.ritavideo.ui.view.MainView
import com.qchemmo.ritavideo.utils.FragmentUtil
import com.qchemmo.ritavideo.utils.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_toolbar.*
import java.nio.file.Files.find


class MainActivity : BaseActivity<MainView,MainPresenter>(),MainView{

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        bottom.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            FragmentUtil.fragmentUtil.getFragment(it.itemId)?.let { it1 ->
                transaction.replace(
                    R.id.container,
                    it1, it.itemId.toString()
                )
            }
            transaction.commitNow()
            return@setOnNavigationItemSelectedListener true
        }

    }

    override fun initData() {
        FragmentUtil.fragmentUtil.getFragment(R.id.play)?.let {
            supportFragmentManager.beginTransaction().replace(R.id.container,
                it,it.toString()).commitNow()
        }
        getPresenter()?.setToolBarTitle("KTPlayer")
    }

    override fun createPresenter(): MainPresenter? =MainPresenter()

    override fun <T> setData(data: T) {
         toolbar.title = data.toString()

    }

    override fun setError(err: String) {
    }

}
