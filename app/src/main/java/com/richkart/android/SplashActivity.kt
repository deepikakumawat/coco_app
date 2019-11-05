package com.richkart.android

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class SplashActivity : AppCompatActivity(){

    private val SPLASH_DISPLAY_LENGTH = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Fabric.with(this, Crashlytics())


        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, StartActivity::class.java)
            this@SplashActivity.startActivity(intent)
            this@SplashActivity.finish()
        },SPLASH_DISPLAY_LENGTH.toLong())


    }
}