package com.qchemmo.mvpretrofit.ui.model

/**
 * @ClassName: MainModel
 * @Description:这里是根据API返回值，编写的data class
 * @Author: chemoontheshy
 * @Date: 2021/3/11-14:56
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