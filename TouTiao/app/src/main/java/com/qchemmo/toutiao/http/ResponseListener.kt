package com.qchemmo.toutiao.http

/**
 * @InterfaceName: ResponseListener
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-16:08
 */
interface ResponseListener<T> {
    fun onSuccess(data: T)
    fun onFail(err: String)
}