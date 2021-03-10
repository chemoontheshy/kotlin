package com.qchemmo.mvpdemo.ui.presenter

import android.content.ContentValues.TAG
import android.util.Log
import com.qchemmo.mvpdemo.base.Constants.WEATHER_URL_TEST
import com.qchemmo.mvpdemo.http.HttpUtils
import com.qchemmo.mvpdemo.http.UserApi
import com.qchemmo.mvpdemo.mvp.presenter.BasePresenter
import com.qchemmo.mvpdemo.ui.view.MainView
import kotlinx.coroutines.*

/**
 * @ClassName: MainPresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-15:13
 */
class MainPresenter:BasePresenter<MainView>(), CoroutineScope by MainScope() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "coroutine: error ${throwable.message}")
    }
    fun getWeather(){
        launch (exceptionHandler){
            val result = withContext(Dispatchers.IO){
                HttpUtils.sendHttp(WEATHER_URL_TEST)
            }
            getBaseView()?.setData(result)

        }
    }


}