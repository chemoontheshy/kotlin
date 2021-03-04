package com.qchemmo.im.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

/**
 * className:BaseActivity
 * description：管理所有Activity的基类
 * author：chemoontheshy
 * date: 2021-03-04
 */
abstract class BaseActivity: AppCompatActivity(),AnkoLogger{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initListener()
        initData()
    }

    /**
     * init data
     */
    protected open fun initData() {

    }

    /**
     * adapter listener
     */

    protected open fun initListener() {

    }
    /**
     *  get Layout id
     */
    abstract fun getLayoutResId(): Int

    /**
     *  toast
     */
    protected fun myToast(msg: String): Boolean {
        runOnUiThread { toast(msg) }
        return true
    }
}