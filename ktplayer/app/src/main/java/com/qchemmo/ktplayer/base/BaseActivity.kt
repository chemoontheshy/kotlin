package com.qchemmo.ktplayer.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())
        initListener()
        initData()
    }

    /**
     * init data
     */
    private fun initData() {

    }

    /**
     * adapter listener
     */

    private fun initListener() {

    }

    /**
     *  get Layout id
     */
    abstract fun getLayoutId(): Int

    protected fun myToast(msg: String) {
        runOnUiThread { toast(msg) }
    }
}