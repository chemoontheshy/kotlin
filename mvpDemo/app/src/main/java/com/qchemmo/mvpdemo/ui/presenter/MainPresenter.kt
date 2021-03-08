package com.qchemmo.mvpdemo.ui.presenter

import com.qchemmo.mvpdemo.mvp.presenter.BasePresenter
import com.qchemmo.mvpdemo.ui.view.MainView

/**
 * @ClassName: MainPresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-15:13
 */
class MainPresenter:BasePresenter<MainView>() {
    fun getTest(str:String){
        getBaseView()?.setData(str)
    }
}