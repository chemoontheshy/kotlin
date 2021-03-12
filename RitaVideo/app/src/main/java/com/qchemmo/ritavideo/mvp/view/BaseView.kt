package com.qchemmo.ritavideo.mvp.view

/**
 * @ClassName: BaseView
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/12-16:14
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