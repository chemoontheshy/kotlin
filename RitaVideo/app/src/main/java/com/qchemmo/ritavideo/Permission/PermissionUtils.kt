package com.qchemmo.ritavideo.Permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import PermissionConstants.READ_AND_WRITE_PERMISSION
import PermissionConstants.RESULT_CODE_OPEN_EXTERNAL_STORAGE
import kotlin.system.exitProcess

/**
 * @ClassName: com.qchemmo.ritavideo.Permission.PermissionUtils
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/17-17:03
 */
object PermissionUtils {
    private var testReadAndWritCallback:(()->Unit)? =null

    /**
     * 读写权限申请
     */
    fun readAndWrite(context: Context,readAndWriteCallback:()->Unit){
        this.testReadAndWritCallback  = readAndWriteCallback
        permission(context,READ_AND_WRITE_PERMISSION,RESULT_CODE_OPEN_EXTERNAL_STORAGE,readAndWriteCallback)
    }
    /**
     * 权限申请结果
     */
    fun onRequestPermissionsResult(resultCode: Int,permissions:Array<out String>,grantResults:IntArray){
        val resultAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED
        when(resultCode){
            RESULT_CODE_OPEN_EXTERNAL_STORAGE->{
                if(resultAccepted){
                    Log.e("result","open success")
                    testReadAndWritCallback?.let { it() }
                }else{
                    Log.e("result","open fail")
                    exitProcess(0)
                }
            }
        }
    }

    /**
     * 权限申请
     */
    private fun permission(context: Context,systemCode:String,resultCode:Int,callback:()->Unit){
        if(ContextCompat.checkSelfPermission(context,systemCode)==PackageManager.PERMISSION_GRANTED){
            callback()
        }else{
            ActivityCompat.requestPermissions(context as Activity, arrayOf(systemCode),resultCode)
        }
    }
}