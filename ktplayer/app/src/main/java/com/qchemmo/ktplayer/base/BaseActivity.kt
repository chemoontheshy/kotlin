package com.qchemmo.ktplayer.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.qchemmo.ktplayer.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
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
    abstract fun getLayoutId(): Int

    protected fun myToast(msg: String): Boolean {
        runOnUiThread { toast(msg) }
        return true
    }

    /**
     *  start a activity and finish the activity
     */
    inline fun <reified T:BaseActivity>startActivityAndFinish(){
        startActivity<T>()
        finish()
    }
}