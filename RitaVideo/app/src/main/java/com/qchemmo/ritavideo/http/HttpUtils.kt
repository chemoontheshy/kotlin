package com.qchemmo.ritavideo.http

import com.qchemmo.ritavideo.base.Constants.WEATHER_URL
import com.qchemmo.ritavideo.base.Constants.WEATHER_URL_TEST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @ClassName: HttpUtils
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/12-16:12
 */
object HttpUtils {
    /**
     * @name: isTest
     * @Description: 判断是否是test的API接口，方便可以省略
     */
    private fun isTest(isTest:Boolean):String = if(isTest) WEATHER_URL_TEST else WEATHER_URL

    fun<T>createApi(clazz: Class<T>):T = Retrofit.Builder()
        .baseUrl(isTest(true))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(clazz)
}