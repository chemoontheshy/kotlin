package com.qchemmo.toutiao.ui.main.presenter

import com.qchemmo.toutiao.http.HttpUtils
import com.qchemmo.toutiao.http.ResponseListener
import com.qchemmo.toutiao.http.UserApi
import com.qchemmo.toutiao.mvp.model.BaseModel
import com.qchemmo.toutiao.mvp.presenter.BasePresenter
import com.qchemmo.toutiao.ui.main.model.MainModel
import com.qchemmo.toutiao.ui.main.view.MainView

/**
 * @ClassName: MainPresenter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-10:59
 */
class MainPresenter:BasePresenter<MainView>() {
    fun getTest(str:String){
       HttpUtils.sendHttp(HttpUtils.createApi(UserApi::class.java).getTest(),object:ResponseListener<BaseModel<MainModel>>{
           override fun onSuccess(data: BaseModel<MainModel>) {
               if(data!=null){
                   getBaseView()?.setData(data)
               }
            }

           override fun onFail(err: String) {
               getBaseView()?.setData(err)
                }

       } )

    }
}