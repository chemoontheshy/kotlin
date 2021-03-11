package com.qchemmo.mvpretrofit.mvp.view

/**
 * @InterfaceName: BaseView
 * @Description:更新UI的接口方法
 * @Author: chemoontheshy
 * @Date: 2021/3/11-14:02
 */
interface BaseView {
    /**
     * 设置数据，更新UI
     */
    fun <T>setData(data:T)

    /**
     * 设置错误数据，更新UI
     */
    fun setError(err:String)
}