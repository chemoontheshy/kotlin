package com.qchemmo.mvpdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qchemmo.mvpdemo.mvp.presenter.BasePresenter
import com.qchemmo.mvpdemo.mvp.view.BaseView

/**
 * @ClassName: BaseActivity
 * @Description:所有Activity的基类，并封装好mvp
 * @Author: chemoontheshy
 * @Date: 2021/3/8-14:46
 */
abstract class BaseActivity<V, P : BasePresenter<V>> : AppCompatActivity(), BaseView {
    private var mPresenter: P? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (mPresenter == null) {
            mPresenter = createPresenter()
        }
        mPresenter?.bindView(this as V)
        init()
        initData()
    }

    /**
     * 1.获取LayoutId
     * 2.初始化
     * 3.初始化数据
     * 4.创建createPresenter
     * 5.让activity获得presenter的方法
     * 6.当activity销毁时，解绑
     */
    protected abstract fun getLayoutId(): Int
    protected abstract fun init()
    protected abstract fun initData()
    protected abstract fun createPresenter(): P
    fun getPresenter() = mPresenter
    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.unBindViw()
    }
}