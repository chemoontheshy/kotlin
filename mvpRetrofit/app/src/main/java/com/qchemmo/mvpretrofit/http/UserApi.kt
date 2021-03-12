package com.qchemmo.mvpretrofit.http

import com.qchemmo.mvpretrofit.mvp.model.BaseModel
import com.qchemmo.mvpretrofit.ui.model.MainModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @InterfaceName: UserApi
 * @Description:请求API的接口，可以多个接口，根据请求的不同
 * @Author: chemoontheshy
 * @Date: 2021/3/11-13:46
 */
interface UserApi {
    /**
     * 注意问题：
     * 1. 加入@FormUrlEncoded 只支持x-www-form-urlencoded不支持form-data所致,时加入这个
     *    如果不加人，会报错，正常请求应该不需要加
     * 2. BaseModel<MainModel>：BaseModel<T>是Model的基类，参考另外文章的data class
     * 3. MainModel是activity的MVP里的M层，具体内容是请求api返回的格式
     */
    @FormUrlEncoded
        @POST("api/tianqi")
        suspend fun getWeather(
            @Field("city") city: String,
            @Field("type") type: Int
        ): BaseModel<MainModel>
}