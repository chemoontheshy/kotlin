package com.qchemmo.ffplay.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.toast

/**
 * ClassName:BaseActivity
 *  Description：所有Activity的基类
 *  author: chemoontheshy
 *  date:2021-3-3
 */

abstract class BaseActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
    }

    /**
     * 初始化数据
     */
    protected open fun initData() {

    }

    /**
     * 初始化监听
     */
    private fun initListener() {

    }

    /**
     * 获取Layout id
     */
    abstract fun getLayoutId(): Int

    protected fun myToast(msg:String){
        runOnUiThread { toast(msg) }
    }

}