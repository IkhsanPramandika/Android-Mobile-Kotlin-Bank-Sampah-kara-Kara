package com.example.uas_mobile.activity

import   RiwayatAdapter
import RiwayatViewModel
import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_mobile.R
import com.example.uas_mobile.database.modelDatabase
import com.example.uas_mobile.utils.FunctionHelper
import com.example.uas_mobile.databinding.ActivityRiwayatBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class RiwayatActivity : AppCompatActivity(), RiwayatAdapter.RiwayatAdapterCallback {

    var modelDatabaseList: MutableList<modelDatabase> = ArrayList()
    lateinit var riwayatAdapter: RiwayatAdapter
    lateinit var riwayatViewModel: RiwayatViewModel
    private lateinit var binding: ActivityRiwayatBinding
    private val productList: MutableList<modelDatabase> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_riwayat)
        setStatusBar()
        setToolbar()
        setInitLayout()
        setViewModel()

        val recyclerView: RecyclerView = binding.rvHistory
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1 )

        recyclerView.adapter = riwayatAdapter

        fetchDataFromFirebase()

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            onBackPressed()
        }

    }

    private fun fetchDataFromFirebase() {
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("products")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()

                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(modelDatabase::class.java)
                    product?.let { productList.add(it) }
                }

                // Refresh tampilan RecyclerView dengan adapter
//                RiwayatAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun setInitLayout() {
        binding.tvNotFound.setVisibility(View.GONE)
        riwayatAdapter = RiwayatAdapter(this, modelDatabaseList, this)
        binding.rvHistory.setHasFixedSize(true)
        binding.rvHistory.setLayoutManager(LinearLayoutManager(this))
        binding.rvHistory.setAdapter(riwayatAdapter)
    }

    private fun setViewModel() {
        riwayatViewModel = ViewModelProvider(this).get(RiwayatViewModel::class.java)

        riwayatViewModel.totalSaldo.observe(this, { integer ->
            if (integer == null) {
                val jumlahSaldo = 0
                val initSaldo = FunctionHelper.rupiahFormat(jumlahSaldo)
                binding.tvSaldo.text = initSaldo
            } else {
                val initSaldo = FunctionHelper.rupiahFormat(integer)
                binding.tvSaldo.text = initSaldo
            }
        })

        riwayatViewModel.dataBank.observe(this, { modelDatabases: List<modelDatabase> ->
            if (modelDatabases.isEmpty()) {
                binding.tvNotFound.visibility = View.VISIBLE
                binding.rvHistory.visibility = View.GONE
            } else {
                binding.tvNotFound.visibility = View.GONE
                binding.rvHistory.visibility = View.VISIBLE
            }
            riwayatAdapter.setDataAdapter(modelDatabases)
        })
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onDelete(data: modelDatabase) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Hapus riwayat ini?")
        alertDialogBuilder.setPositiveButton("Ya, Hapus") { dialogInterface: DialogInterface?, i: Int ->
            val id = data.id
            riwayatViewModel.deleteDataById(id)
            Toast.makeText(
                this@RiwayatActivity,
                "Data yang dipilih sudah dihapus",
                Toast.LENGTH_SHORT
            ).show()
        }
        alertDialogBuilder.setNegativeButton("Batal") { dialogInterface: DialogInterface, i: Int -> dialogInterface.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    override fun onItemClick(data: modelDatabase) {
        // Handle item click here
        // This method will be called when an item in the RecyclerView is clicked
        // You can implement your logic here to respond to the item click
        // For example, you can navigate to another activity or show a dialog with more details about the clicked item
        // 'data' parameter contains the clicked item's data (modelDatabase object)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }
}

