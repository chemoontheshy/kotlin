package com.qchemmo.mvpretrofit.ui.presenter

import android.content.ContentValues
import android.content.ContentValues.TAG
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
 * @Description:P层，提供不涉及数据M层的方法，和涉及M层的方法
 * @Author: chemoontheshy
 * @Date: 2021/3/11-14:56
 */
class MainPresenter: BasePresenter<MainView>() {
    fun setText(){
        getBaseView()?.setData("test")
    }

    /**
     * launch 是协程里子线程的挂起，反正相当于创建一个子线程，不会阻塞主线程，另外还有async
     * exceptionHandler 是请求过程中发生错误，处理异常的方法
     * response:BaseModel<MainModel> 是通过基类BaseModel,和实际的M层组成“返回类容”
     * HttpUtils.createApi(UserApi::class.java).getWeather("长沙",1)
     */
    fun getWeather(){
        launch (exceptionHandler){
            val response:BaseModel<MainModel> = HttpUtils.createApi(UserApi::class.java).getWeather("长沙",1)
            Log.e(TAG,response.toString())
            val cityName = response.data.cityname
            val temp = response.data.temp
            Log.e(TAG,cityName+temp)
            getBaseView()?.setData(response)
        }
    }
}