package com.qchemmo.mvpretrofit.mvp.presenter

import android.content.ContentValues
import android.util.Log
import com.qchemmo.mvpretrofit.mvp.view.BaseView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**
 * @ClassName: BasePresenter
 * @Description:Presenter的基类，主要是绑定，解绑，提供方法接口，增加协程
 * @Author: chemoontheshy
 * @Date: 2021/3/11-14:01
 */
open class BasePresenter<V> : CoroutineScope by MainScope(){
    /**
     * 在基类里，提供协程出现错位的调用
     */
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(ContentValues.TAG, "coroutine: error ${throwable.message}")
    }

    private var mBaseView:V? = null
    /**
     * 绑定View
     */
    fun bindView(mBaseView:V){
        this.mBaseView = mBaseView
    }
    /**
     * 解绑View
     */
    fun unbindView(){
        this.mBaseView = null
    }
    /**
     * 返回方法的接口
     */
    fun getBaseView() = mBaseView
}