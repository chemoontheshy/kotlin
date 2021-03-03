package com.qchemmo.ffplay.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qchemmo.ffplay.R
import com.qchemmo.ffplay.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        sample_text.text = test()
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    external fun test():String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("play")
        }
    }
}
