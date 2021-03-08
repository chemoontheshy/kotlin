package com.qchemmo.toutiao.http

import androidx.annotation.MainThread
import com.qchemmo.toutiao.mvp.model.BaseModel
import com.qchemmo.toutiao.ui.main.model.MainModel
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET

/**
 * @InterfaceName: UserApi
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-16:27
 */
interface UserApi {
    @GET("journalismApi")
    fun getTest():Observable<BaseModel<MainModel>>
}