package com.qchemmo.testdemo1

import android.content.pm.PackageManager
import android.net.Uri
import android.net.Uri.parse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

const val REQUEST_WRITE_EXTERNAL_STORAGE = 1
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val videoView = findViewById<VideoView>(R.id.videoView)
        //Creating MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //specify the location of media file
        handlePermissions()
        //val uri:Uri = parse(getExternalFilesDir("test.mp4")?.path)
        val uri:Uri = parse(Environment.getExternalStoragePublicDirectory("/kotlin/test.mp4").path)
        Log.e("uri",uri.toString())
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
    }

    private fun handlePermissions(){
        val permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        val checkSelfPermission = this.checkSelfPermission(permission)
        if (checkSelfPermission==PackageManager.PERMISSION_GRANTED){
            Log.e("permission","have permission")
        }
        else{
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_WRITE_EXTERNAL_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_WRITE_EXTERNAL_STORAGE->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"已申请权限",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"申请权限失败",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
