package com.tranced.pusheendailykt.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tranced.pusheendailykt.Constants.Companion.SPLASH_DELAY
import com.tranced.pusheendailykt.Constants.Companion.SPLASH_DELAY_WHAT
import com.tranced.pusheendailykt.R
import com.tranced.pusheendailykt.main.view.MainActivity

/**
 * SplashActivity
 * @author TranceD
 */
class SplashActivity : AppCompatActivity() {
    private lateinit var splashImage: ImageView;
    //TODO 使用协程完成
    private val handler: Handler = object: Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SPLASH_DELAY_WHAT -> startMainActivity()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        initSplashImage()
        handler.sendEmptyMessageDelayed(SPLASH_DELAY_WHAT, SPLASH_DELAY)
    }

    private fun initSplashImage() {
        splashImage = findViewById(R.id.splash_image)
        Glide.with(this).load(R.drawable.splasheen).into(splashImage)
    }

    private fun startMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
