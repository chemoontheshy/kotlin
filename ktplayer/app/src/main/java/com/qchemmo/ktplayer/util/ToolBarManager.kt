package com.qchemmo.ktplayer.util

import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import com.qchemmo.ktplayer.R

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
        toolBar.setTitle("KTplayer")
        toolBar.inflateMenu(R.menu.main)
        toolBar.setOnMenuItemClickListener(object:Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(p0: MenuItem?):Boolean {
               when(p0?.itemId){
                   R.id.setting->{
                       Toast.makeText(toolBar.context,"点击了设置按钮",Toast.LENGTH_SHORT).show()
                   }
               }
                return true
            }

        })
    }
}