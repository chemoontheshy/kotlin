package com.qchemmo.toutiao.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qchemmo.toutiao.mvp.presenter.BasePresenter
import com.qchemmo.toutiao.mvp.view.BaseView

/**
 * @ClassName: BaseActivity
 * @Description:抽象类
 * @Author: chemoontheshy
 * @Date: 2021/3/8-10:19
 */
abstract class BaseActivity<V, P : BasePresenter<V>> : AppCompatActivity(), BaseView {
    //把BasePresenter当成变量
    private var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        // 当mPresenter为空的时候，创建mPresenter
        if (mPresenter == null) {
            mPresenter = createPresenter()
        }
        // 绑定
        mPresenter!!.bindView(this as V)
        init()
        initData()
    }
    /**
     * 获得Layout的Id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 初始化函数
     */
    protected abstract fun init()



    /**
     * 创建Presenter
     */
    protected abstract fun createPresenter():P

    /**
     * 让activity获得Presenter的方法
     */
    fun getPresenter() = mPresenter

    /**
     * 当activity被销毁的时候，解绑
     */
    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unBindView()
    }
}