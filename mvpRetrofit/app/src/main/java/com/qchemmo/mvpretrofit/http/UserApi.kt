package com.qchemmo.mvpretrofit.http

import com.qchemmo.mvpretrofit.mvp.model.BaseModel
import com.qchemmo.mvpretrofit.ui.model.MainModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @InterfaceName: UserApi
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/11-13:46
 */
interface UserApi {
    @FormUrlEncoded  //只支持x-www-form-urlencoded不支持form-data所致,时加入这个
        @POST("api/tianqi")
        suspend fun getWeather(
            @Field("city") city: String,
            @Field("type") type: Int
        ): BaseModel<MainModel>


}