package com.qchemmo.im.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * ClassName:BaseFragment
 * Description:所有fragment的基类
 */

abstract class BaseFragment : Fragment(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * Fragment 初始化
     */
    private fun init() {

     }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return initView()
    }

    /**
     * Fragment 获取布局view
     */
    abstract fun initView(): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLister()
        initData()
    }

    /**
     * 数据的初始化
     */
    protected open fun initData() {

    }

    /**
     * adapter listener
     */
    protected open  fun initLister() {

    }

    fun myToast(msg:String){
        context?.runOnUiThread { toast(msg) }
    }
}