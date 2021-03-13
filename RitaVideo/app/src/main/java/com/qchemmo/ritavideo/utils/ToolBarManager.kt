package com.qchemmo.ritavideo.utils

import android.widget.Toolbar

/**
 * @InterfaceName: ToolBarManager
 * @Description:所有toolbar的管理类
 * @Author: chemoontheshy
 * @Date: 2021/3/13-14:41
 */
interface ToolBarManager {
    var toolbar:Toolbar
    /**
     * 初始化主界面的toolbar
     */
    fun initMainToolBar(){
        toolbar.title = "we"
    }
}