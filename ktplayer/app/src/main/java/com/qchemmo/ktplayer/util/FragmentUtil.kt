package com.qchemmo.ktplayer.util

import com.qchemmo.ktplayer.R
import com.qchemmo.ktplayer.base.BaseFragment
import com.qchemmo.ktplayer.ui.fragment.HomeFragment
import com.qchemmo.ktplayer.ui.fragment.MvFragment
import com.qchemmo.ktplayer.ui.fragment.VBangFragment
import com.qchemmo.ktplayer.ui.fragment.YueDanFragment

/**
 * ClassName:FragmentUtil
 * Description:管理fragment的util类
 */

class FragmentUtil private constructor() {
    //私有化构造方法
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YueDanFragment() }
    companion object {
        val fragmentUtil by lazy { FragmentUtil() }
    }

    fun getFragment(tabId:Int): BaseFragment? {
        when(tabId){
            R.id.Home->return homeFragment
            R.id.Mv->return mvFragment
            R.id.VBang->return vbangFragment
            R.id.YueDan->return yuedanFragment
        }
        return null
    }

}