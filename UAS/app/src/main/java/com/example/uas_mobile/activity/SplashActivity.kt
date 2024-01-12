package com.example.uas_mobile.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.uas_mobile.R
import com.example.uas_mobile.onboarding.Onboarding1Activity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, Onboarding1Activity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // Delay 3000ms atau 3 detik sebelum memulai MainActivity

}
    }
