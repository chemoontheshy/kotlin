package com.qchemmo.ktplayer.util

import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import com.qchemmo.ktplayer.R
import com.qchemmo.ktplayer.ui.activity.SettingActivity

/**
 * ClassName:ToolBarManager
 * Description:所有界面的toolbar的管理类
 */

interface ToolBarManager {
    val toolBar:Toolbar

    /**
     * 初始化主界面中的ToolBar
     */
    fun initMainToolBar(){
        toolBar.title = "KTplayer"
        toolBar.inflateMenu(R.menu.main)
        //如果java接口只有一个未实现的方法，可以省略接口对象，直接使用｛｝表示未实现的方法
        toolBar.setOnMenuItemClickListener {
            toolBar.context.startActivity(Intent(Intent(toolBar.context,SettingActivity::class.java)))
            true
        }
    }


    /**
     * 处理设置界面的toolbar
     */
    fun initSettingToolbar(){
        toolBar.title = "设置界面"
    }
}