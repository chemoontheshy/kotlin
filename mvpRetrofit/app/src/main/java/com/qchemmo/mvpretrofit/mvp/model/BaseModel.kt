package com.qchemmo.mvpretrofit.mvp.model

/**
 * @ClassName: BaseModel
 * @Description:Model层的基类，<T>泛型，
 * @Author: chemoontheshy
 * @Date: 2021/3/11-14:00
 * @Remake: 一般网络请求的API返回值都是这的结构，所以提取为基类,<T>是实际请求数据的类型
 */
data class BaseModel<T>(val code:Int,
                        val  message:String,
                        val data:T)