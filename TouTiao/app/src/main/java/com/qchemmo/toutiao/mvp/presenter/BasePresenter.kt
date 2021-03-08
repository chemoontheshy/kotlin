package com.qchemmo.toutiao.mvp.presenter

/**
 * @ClassName: BasePresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-10:06
 */
open class BasePresenter<V> {
    private var mBaseView:V? = null

    /**
     * 绑定View
     */
    fun bindView(mBaseView: V){
        this.mBaseView = mBaseView
    }

    /**
     * 解绑View
     */
    fun unBindView(){
        this.mBaseView = null
    }

    /**
     * 返回View
     */
    fun getBaseView() = mBaseView
}