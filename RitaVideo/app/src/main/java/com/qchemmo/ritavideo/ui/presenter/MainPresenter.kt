package com.qchemmo.ritavideo.ui.presenter

import android.widget.Toolbar
import com.qchemmo.ritavideo.mvp.presenter.BasePresenter
import com.qchemmo.ritavideo.ui.view.MainView

/**
 * @ClassName: MainPresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/12-16:33
 */
class MainPresenter: BasePresenter<MainView>() {
    fun setToolBarTitle(string: String){
       getBaseView()?.setData(string)
    }
}