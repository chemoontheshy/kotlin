package com.qchemmo.toutiao.ui.main.presenter

import com.qchemmo.toutiao.mvp.presenter.BasePresenter
import com.qchemmo.toutiao.ui.main.view.MainView

/**
 * @ClassName: MainPresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-10:59
 */
class MainPresenter:BasePresenter<MainView>() {
    fun getTest(str:String){
        getBaseView()?.setData(str)

    }
}