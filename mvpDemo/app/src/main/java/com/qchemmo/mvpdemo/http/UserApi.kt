package com.qchemmo.mvpdemo.http

import com.qchemmo.mvpdemo.mvp.mode.BaseModel
import com.qchemmo.mvpdemo.ui.model.MainModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

/**
 * @InterfaceName: UserApi
 * @Description:封装成的Api
 * @Author: chemoontheshy
 * @Date: 2021/3/9-11:50
 */
interface UserApi {
    @GET("tianqi?city=长沙&type=1")
    suspend fun getWeather2():BaseModel<MainModel>

}