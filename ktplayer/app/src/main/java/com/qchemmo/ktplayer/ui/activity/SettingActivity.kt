package com.qchemmo.ktplayer.ui.activity

import android.widget.Toolbar
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
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
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val push = sp.getBoolean("push",false)
        println("push=$push")
    }

}