package com.qchemmo.ktplayer.ui.activity

import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.qchemmo.ktplayer.R
import com.qchemmo.ktplayer.base.BaseActivity
import com.qchemmo.ktplayer.util.FragmentUtil
import com.qchemmo.ktplayer.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.find
import java.nio.file.Files.find

class MainActivity : BaseActivity(), ToolBarManager {
    override val toolBar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {
//        设置tab切换监听
        bottomBar.setOnNavigationItemSelectedListener {
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


}

//val transaction = supportFragmentManager.beginTransaction()
//FragmentUtil.fragmentUtil.getFragment(it.itemId)?.let { it1 ->
//    transaction.replace(
//        R.id.container,
//        it1, it.itemId.toString()
//    )
//}
//transaction.commitNow()
