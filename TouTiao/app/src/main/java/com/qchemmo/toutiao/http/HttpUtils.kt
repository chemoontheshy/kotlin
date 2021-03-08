package com.qchemmo.toutiao.http

import android.database.Observable
import com.orhanobut.logger.Logger
import com.qchemmo.toutiao.base.Constants.BASE_URL
import com.qchemmo.toutiao.base.Constants.BASE_URL_TEST
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @ClassName: HttpUtils
 * @Description:静态类
 * @Author: chemoontheshy
 * @Date: 2021/3/8-12:10
 */
object HttpUtils {
    private var mOkhttp: OkHttpClient? = null
    private fun isTest(isTest: Boolean): String = if (isTest) BASE_URL_TEST else BASE_URL

    fun <T> createApi(clazz: Class<T>): T = Retrofit.Builder()
        .baseUrl(isTest(true))
        .client(getClient()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(clazz)

    private fun getClient(): OkHttpClient? {
        if (mOkhttp == null) {
            mOkhttp = OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .retryOnConnectionFailure(true)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
        }
        return mOkhttp
    }

    private fun getInterceptor(): Interceptor {
        Logger.json(HttpLoggingInterceptor.Logger.toString())
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun <T> sendHttp(ob: io.reactivex.Observable<T>, linstener: ResponseListener<T>) {
            ob.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<T>{
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                     }

                    override fun onNext(t: T) {
                        linstener.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        linstener.onFail(e.message.toString())
                    }

                })
    }


}