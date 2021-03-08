package com.qchemmo.toutiao.mvp.view

/**
 * @InterfaceName: BaseView
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-10:06
 */
interface BaseView {
    fun <T>setData(data:T)
    fun setError(err: String)
}