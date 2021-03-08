package com.qchemmo.mvpdemo.mvp.mode

/**
 * @ClassName: BaseModel
 * @Description:MVP模式里的Model层的基类,<T>泛型形参声明
 * @Author: chemoontheshy
 * @Date: 2021/3/8-14:31
 */
data class BaseModel<T>(val code:Int,val message:String,val data:T)