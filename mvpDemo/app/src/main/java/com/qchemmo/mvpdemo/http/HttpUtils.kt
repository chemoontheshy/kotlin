package com.qchemmo.mvpdemo.http

import android.util.Log
import com.qchemmo.mvpdemo.base.Constants.WEATHER_URL
import com.qchemmo.mvpdemo.base.Constants.WEATHER_URL_TEST
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

/**
 * @ClassName: HttpUtils
 * @Description:静态对象，封装网络请求
 * @Author: chemoontheshy
 * @Date: 2021/3/9-11:47
 */
object HttpUtils {
    private var mClient:OkHttpClient?=null
    private fun isTest(isTest:Boolean):String = if (isTest) WEATHER_URL_TEST else WEATHER_URL

    fun <T>createApi(clazz: Class<T>):T = Retrofit.Builder()
        .baseUrl(isTest(true))
        .client(getClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(clazz)

    private fun getClient(): OkHttpClient? {
        if (mClient == null) {
            mClient = OkHttpClient.Builder()
                //拦截器
                .addInterceptor(getInterceptor())
                .retryOnConnectionFailure(true)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
        }
        return mClient

    }

    private fun getInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun <T> sendHttp(ob:io.reactivex.Observable<T>,listener: ResponseListener<T>){
        ob.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<T>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                 }

                override fun onNext(t: T) {
                    listener.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    listener.onFail(e.message.toString())
                }
            })
    }

}