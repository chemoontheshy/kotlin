package com.qchemmo.ktplayer.widget

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import com.qchemmo.ktplayer.R

/**
 * ClassName:HomeItemView
 * Description:
 *
 */

class HomeItemView(context: Context?) : RelativeLayout(context) {
    /**
     * 初始化的方法
     */
    init {
        View.inflate(context, R.layout.item_home,this)

    }

}