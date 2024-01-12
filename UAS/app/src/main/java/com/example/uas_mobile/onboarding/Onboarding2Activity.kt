package com.example.uas_mobile.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.uas_mobile.R
import com.example.uas_mobile.activity.ActivityLoginUser
import com.example.uas_mobile.activity.ActivityRegister

class Onboarding2Activity : AppCompatActivity ( ){

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide2)

        val buyNowButton = findViewById<Button>(R.id.btn_facebook)
        buyNowButton.setOnClickListener {
            // Pindah ke ActivityBuyProduct
            val intent = Intent(applicationContext, Onboarding3Activity::class.java)
            // Anda mungkin perlu mengirimkan data tambahan jika diperlukan
            // intent.putExtra("key", value)
            startActivity(intent)

            val registerButton = findViewById<Button>(R.id.btn_google)
            registerButton.setOnClickListener {
                // Pindah ke ActivityBuyProduct
                val intent = Intent(applicationContext, ActivityRegister::class.java)
                // Anda mungkin perlu mengirimkan data tambahan jika diperlukan
                // intent.putExtra("key", value)
                startActivity(intent)
            }
        }
    }
}
