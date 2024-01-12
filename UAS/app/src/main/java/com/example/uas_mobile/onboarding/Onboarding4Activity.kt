package com.example.uas_mobile.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.uas_mobile.R
import com.example.uas_mobile.activity.ActivityLoginUser
import com.example.uas_mobile.activity.ActivityRegister

class Onboarding4Activity : AppCompatActivity( ){

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide4)

        val buyNowButton = findViewById<Button>(R.id.btn_facebook)
        buyNowButton.setOnClickListener {
            // Pindah ke ActivityBuyProduct
            val intent = Intent(applicationContext, ActivityLoginUser::class.java)
            // Anda mungkin perlu mengirimkan data tambahan jika diperlukan
            // intent.putExtra("key", value)
            startActivity(intent)


        }
    }

}
