package com.qchemmo.ritavideo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @ClassName: BaseFragment
 * @Description:所有fragment的基类
 * @Author: chemoontheshy
 * @Date: 2021/3/13-16:26
 */
abstract class BaseFragment:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    protected open fun init() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    abstract  fun initView() :View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    protected open fun initData() {
    }

    protected open fun initListener() {
    }
}