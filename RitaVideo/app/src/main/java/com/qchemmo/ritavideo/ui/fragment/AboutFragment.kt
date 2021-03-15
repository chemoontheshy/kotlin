package com.qchemmo.ritavideo.ui.fragment

import android.view.View
import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

/**
 * @ClassName: PlayFragment
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/13-16:40
 */
class AboutFragment:BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_about,null)
    }
}
