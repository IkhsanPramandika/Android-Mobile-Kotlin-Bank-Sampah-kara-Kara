//package com.example.uas_mobile
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.bumptech.glide.Glide
//import com.example.uas_mobile.databinding.ActivityDetailBinding
////import com.example.uas_mobile.model.ModelModul
//import com.example.uas_mobile.database.modelDatabase
//
//
//@Suppress("DEPRECATION")
//class DetailActivityItem : AppCompatActivity() {
//    private lateinit var binding : ActivityDetailBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val data = intent.getParcelableExtra<modelDatabase>("data")
//        binding.imageView3.setOnClickListener {
//            onBackPressed()
//        }
//
//        val imageComponent = binding.ivGambar
//        Glide.with(imageComponent)
//            .load(data?.avatar)
//            .into(imageComponent)
//
//        binding.tvJudul.text = data?.judul.toString()
//        binding.tvDesc.text = data?.desc.toString()
//
//    }
//}