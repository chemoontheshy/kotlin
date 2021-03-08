package com.qchemmo.toutiao.mvp.model

/**
 * @ClassName: BaseModel
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-10:07
 */
data class BaseModel<T>(val  code:Int,val message:String,val data:T)