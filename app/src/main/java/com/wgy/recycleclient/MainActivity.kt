package com.wgy.recycleclient

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.wgy.recycleclient.ui.activity.LoginActivity
import kotlin.concurrent.thread

class MainActivity : BaseActivity() {
    companion object {const val SPLASH_DISPLAY_LENGTH:Long=2000}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 去除状态栏，如 电量、Wifi信号等
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.main_layout)
        thread {
            Thread.sleep(SPLASH_DISPLAY_LENGTH)
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}