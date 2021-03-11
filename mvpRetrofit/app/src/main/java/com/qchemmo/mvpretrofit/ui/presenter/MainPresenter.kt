package com.qchemmo.mvpretrofit.ui.presenter

import android.content.ContentValues
import android.util.Log
import com.qchemmo.mvpretrofit.http.HttpUtils
import com.qchemmo.mvpretrofit.http.UserApi
import com.qchemmo.mvpretrofit.mvp.model.BaseModel
import com.qchemmo.mvpretrofit.mvp.presenter.BasePresenter
import com.qchemmo.mvpretrofit.ui.model.MainModel
import com.qchemmo.mvpretrofit.ui.view.MainView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @ClassName: MainPresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/11-14:56
 */
class MainPresenter: BasePresenter<MainView>(), CoroutineScope by MainScope() {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(ContentValues.TAG, "coroutine: error ${throwable.message}")
    }
    fun setText(){
        getBaseView()?.setData("test")
    }
    fun getWeather(){
        launch (exceptionHandler){
            val response:BaseModel<MainModel> = HttpUtils.createApi(UserApi::class.java).getWeather("长沙",1)
            Log.e(ContentValues.TAG,response.toString())
            val newData = response.data
            println(newData.cityname)
            getBaseView()?.setData(response)
        }


    }
}