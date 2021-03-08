package com.qchemmo.ktplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.qchemmo.ktplayer.R
import com.qchemmo.ktplayer.adapter.HomeAdapter
import com.qchemmo.ktplayer.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.IOException

class HomeFragment : BaseFragment() {
    override fun initView(): View? {
       return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initLister() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        //适配
        val adapter = HomeAdapter()
        recycler_view.adapter = adapter
    }

    override fun initData() {
        //初始化数据
        loadDatas()
    }

    private fun loadDatas() {
//        val path = "http://t.weather.sojson.com/api/weather/city/101030100"
//        val client = OkHttpClient()
//        val request = Request.Builder().url(path).get().build()
//        client.newCall(request).enqueue(object :Callback{
//            /**
//             * 在子线程中执行
//             */
//            override fun onFailure(call: Call, e: IOException) {
//                println("Get msg error"+Thread.currentThread().name)
//            }
//            /**
//             * 在子线程中执行
//             */
//            override fun onResponse(call: Call, response: Response) {
//                println("Get msg success"+Thread.currentThread().name)
//               }
//        })name
    }
}