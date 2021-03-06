package com.qchemmo.mvpdemo.ui.model

import com.qchemmo.mvpdemo.mvp.mode.BaseModel

/**
 * @ClassName: MainModel
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-15:12
 */
data class MainModel(val cityname:String,
                     val nameen:String,
                     val temp:String,
                     val WD:String,
                     val WS:String,
                     val wse:String,
                     val SD:String,
                     val weather:String,
                     val pm25:String,
                     val limitnumber:String,
                     val time:String)