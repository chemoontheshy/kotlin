package com.qchemmo.mvpdemo.mvp.presenter

import com.qchemmo.mvpdemo.mvp.view.BaseView

/**
 * @ClassName: BasePresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-14:41
 */
open class BasePresenter<V> {
    private var mBaseView:V? = null
    /**
     * 绑定View
     */
    fun bindView(mBaseView:V){
        this.mBaseView = mBaseView
    }
    /**
     * 解绑View
     */
    fun unBindViw(){
        this.mBaseView = null
    }
    /**
     * 返回View
     */
    fun getBaseView() = mBaseView
}