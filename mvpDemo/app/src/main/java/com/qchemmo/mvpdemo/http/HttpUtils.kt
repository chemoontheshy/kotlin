package com.qchemmo.mvpdemo.http

import android.util.Log
import com.qchemmo.mvpdemo.base.Constants.WEATHER_URL
import com.qchemmo.mvpdemo.base.Constants.WEATHER_URL_TEST
import io.reactivex.Observable
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * @ClassName: HttpUtils
 * @Description:静态对象，封装网络请求
 * @Author: chemoontheshy
 * @Date: 2021/3/9-11:47
 */
object HttpUtils{
    private var client: OkHttpClient? = null


    /**
     * 因为okhttp3.ResponseBody的string方法是一个IO操作
     * ，可能会比较耗时。所以我们通过一个挂起函数将这个操作放在后台线程执行。
     */
    private suspend fun getString(response: Response): String {
        return withContext(Dispatchers.IO) {
            response.body?.string() ?: "empty string"
        }
    }

    //为okhttp3增加协程
    private suspend fun Call.awaitResponse(): Response {
        return suspendCancellableCoroutine {
            it.invokeOnCancellation {
                cancel()
            }

            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    it.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    it.resume(response)
                    Log.d("TAG", "onResponse: $response")

                }
            })
        }
    }
    suspend fun sendHttp(url:String): String? {
        val builder:OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
        client = builder.build()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client?.newCall(request)?.awaitResponse()
        return response?.let { getString(it) }
    }
    fun userApi(): UserApi = Retrofit.Builder()
        .baseUrl(WEATHER_URL_TEST)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(UserApi::class.java)

}