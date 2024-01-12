package com.example.uas_mobile.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.uas_mobile.R
import com.example.uas_mobile.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buyNowButton = findViewById<CardView>(R.id.cvKategori)
        buyNowButton.setOnClickListener {
            val intent = Intent(applicationContext, KategoriActivity::class.java)
            startActivity(intent)
        }

        val buySampah = findViewById<CardView>(R.id.cvInput)
        buySampah.setOnClickListener {
            val intent = Intent(applicationContext, InputDataActivity::class.java)
            startActivity(intent)
        }

        val saldo = findViewById<CardView>(R.id.cvHistory)
        saldo.setOnClickListener {
            val intent = Intent(applicationContext, RiwayatActivity::class.java)
            startActivity(intent)
        }


//            // Tambahkan listener untuk setiap item pada bottom navigation
//            binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
//                when (menuItem.itemId) {
//                    R.id.nav_beranda -> {
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    }
//
//                    R.id.nav_riwayat -> {
//                        val intent = Intent(this, RiwayatActivity::class.java)
//                        startActivity(intent)
//                    }
//
//                    R.id.nav_akun -> {
//                        val intent = Intent(this, AccountActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
//                false
//            }
//        }


//        private fun replaceFragment(fragment: Fragment) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frame_layout, fragment)
//                .commit()
//        }
    }
    }

