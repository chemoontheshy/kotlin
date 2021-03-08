package com.qchemmo.toutiao.base

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * @ClassName: BaseApp
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/8-12:03
 */
class BaseApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}