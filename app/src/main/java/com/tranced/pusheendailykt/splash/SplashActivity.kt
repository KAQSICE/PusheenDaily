package com.tranced.pusheendailykt.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tranced.pusheendailykt.Constants.Companion.SPLASH_DELAY
import com.tranced.pusheendailykt.Constants.Companion.SPLASH_DELAY_INTERVAL
import com.tranced.pusheendailykt.R
import com.tranced.pusheendailykt.main.view.MainActivity

/**
 * SplashActivity
 * @author TranceD
 */
class SplashActivity : AppCompatActivity() {
    private lateinit var splashImage: ImageView
    private val countDownTimer: CountDownTimer
        get() = object : CountDownTimer(SPLASH_DELAY, SPLASH_DELAY_INTERVAL) {
            override fun onFinish() {
                startMainActivity()
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        initSplashImage()
    }

    private fun initSplashImage() {
        splashImage = findViewById(R.id.splash_image)
        Glide.with(this).load(R.drawable.splasheen).into(splashImage)
        countDownTimer.start()
    }

    private fun startMainActivity() {
        finish()
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
