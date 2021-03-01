package com.qchemmo.ktplayer.ui.activity

import android.widget.Toolbar
import com.qchemmo.ktplayer.R
import com.qchemmo.ktplayer.base.BaseActivity
import com.qchemmo.ktplayer.util.ToolBarManager
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.find
import java.nio.file.Files.find

class MainActivity : BaseActivity(),ToolBarManager{
    override val toolBar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }


}


