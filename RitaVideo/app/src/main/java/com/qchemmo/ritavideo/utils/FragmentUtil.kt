package com.qchemmo.ritavideo.utils

import com.qchemmo.ritavideo.R
import com.qchemmo.ritavideo.base.BaseFragment
import com.qchemmo.ritavideo.ui.fragment.AboutFragment
import com.qchemmo.ritavideo.ui.fragment.PictureFragment
import com.qchemmo.ritavideo.ui.fragment.PlayFragment
import com.qchemmo.ritavideo.ui.fragment.VideoFragment

/**
 * @ClassName: FragmentUtil
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/15-10:24
 */
class FragmentUtil private constructor(){
    private val playFragment by lazy { PlayFragment() }
    private val pictureFragment by lazy { PictureFragment() }
    private val videoFragment by lazy { VideoFragment() }
    private val aboutFragment by lazy { AboutFragment() }
    companion object{
        val fragmentUtil by lazy { FragmentUtil() }
    }
    fun getFragment(tabId:Int):BaseFragment?{
        when(tabId){
            R.id.play->return playFragment
            R.id.picture->return pictureFragment
            R.id.video->return videoFragment
            R.id.about->return aboutFragment
        }
        return null
    }
}