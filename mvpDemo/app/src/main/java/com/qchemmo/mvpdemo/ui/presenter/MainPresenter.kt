package com.qchemmo.mvpdemo.ui.presenter

import android.util.Log
import com.qchemmo.mvpdemo.base.Constants.WEATHER_URL_TEST
import com.qchemmo.mvpdemo.http.HttpUtils
import com.qchemmo.mvpdemo.http.ResponseListener
import com.qchemmo.mvpdemo.http.UserApi
import com.qchemmo.mvpdemo.mvp.mode.BaseModel
import com.qchemmo.mvpdemo.mvp.presenter.BasePresenter
import com.qchemmo.mvpdemo.mvp.view.BaseView
import com.qchemmo.mvpdemo.ui.model.MainModel
import com.qchemmo.mvpdemo.ui.view.MainView
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * @ClassName: MainPresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-15:13
 */
class MainPresenter:BasePresenter<MainView>() {
    fun getTest(str:String){
        getBaseView()?.setData(str)
    }

    fun getData() {
        var client:OkHttpClient
        var builder:OkHttpClient.Builder
        builder = OkHttpClient.Builder()
            .readTimeout(5,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
        client = builder.build()
        var request = Request.Builder()
            .url(WEATHER_URL_TEST)
            .build()
        client.newCall(request)
            .enqueue(object :okhttp3.Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("TAG", "onFailure: ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    var dataStr = response.body?.string()
                    Log.d("TAG", "onResponse: ${dataStr}")
                  }

            })
    }
}