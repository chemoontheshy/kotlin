package com.qchemmo.ritavideo.mvp.model

/**
 * @ClassName: BaseModel
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/12-16:13
 */
data class BaseModel<T>(val code:Int,
                        val  message:String,
                        val data:T)