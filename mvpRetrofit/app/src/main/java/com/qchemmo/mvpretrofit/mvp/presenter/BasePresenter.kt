package com.qchemmo.mvpretrofit.mvp.presenter

import com.qchemmo.mvpretrofit.mvp.view.BaseView

/**
 * @ClassName: BasePresenter
 * @Description:Presenter的基类，主要是绑定，解绑，提供方法接口
 * @Author: chemoontheshy
 * @Date: 2021/3/11-14:01
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
    fun unbindView(){
        this.mBaseView = null
    }
    /**
     * 返回方法的接口
     */
    fun getBaseView() = mBaseView
}