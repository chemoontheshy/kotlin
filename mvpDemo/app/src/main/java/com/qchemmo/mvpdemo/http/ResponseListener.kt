package com.qchemmo.mvpdemo.http

/**
 * @InterfaceName: ResponseListener
 * @Description:网络请求返回的方法
 * @Author: chemoontheshy
 * @Date: 2021/3/9-11:48
 */
interface ResponseListener<T> {
    fun onSuccess(data: T)
    fun onFail(err:String)
}