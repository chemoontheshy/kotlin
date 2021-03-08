package com.qchemmo.mvpdemo.mvp.view

/**
 * @InterfaceName: BaseView
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-14:35
 */
interface BaseView {
    /**
     * 设置数据
     */
    fun <T>setData(data:T)
    /**
     * 设置错误
     */
    fun setError(err:String)
}