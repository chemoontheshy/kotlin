package com.qchemmo.ktplayer.ui.activity

import android.widget.Toolbar
import com.qchemmo.ktplayer.R
import com.qchemmo.ktplayer.base.BaseActivity
import com.qchemmo.ktplayer.util.ToolBarManager
import org.jetbrains.anko.find

class SettingActivity: BaseActivity(),ToolBarManager{
    override val toolBar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolbar()
    }

}