package com.example.uas_mobile.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.uas_mobile.R
import com.example.uas_mobile.database.User
import com.example.uas_mobile.database.modelDatabase
import com.example.uas_mobile.utils.FunctionHelper.rupiahFormat
//import com.azhar.banksampah.viewmodel.InputDataViewModel
//import kotlinx.android.synthetic.main.activity_input_data.*
import java.text.SimpleDateFormat
import java.util.*
import com.example.uas_mobile.databinding.ActivityInputDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class JualSampahActivity : AppCompatActivity() {


    ////    lateinit var inputDataViewModel: InputDataViewModel
    lateinit var strNama: String
    lateinit var strTanggal: String
    lateinit var strAlamat: String
    lateinit var strCatatan: String
    lateinit var strKategoriSelected: String
    lateinit var strHargaSelected: String
    lateinit var strKategori: Array<String>
    lateinit var strHarga: Array<String>
    var countTotal = 0
    var countBerat = 0
    var countHarga = 0


    private lateinit var binding: ActivityInputDataBinding
    private lateinit var ref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref = FirebaseDatabase.getInstance().getReference("dataInput")

        setStatusBar()
        setToolbar()
        setInitLayout()
        setInputData()
    }

    fun setToolbar() {
        setSupportActionBar(binding.toolbarInputData)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }
    //
    fun setInitLayout() {
        strKategori = resources.getStringArray(R.array.kategori_sampah)
        strHarga = resources.getStringArray(R.array.harga_perkilo)

        val arrayBahasa = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, strKategori)
        arrayBahasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spKategori.setAdapter(arrayBahasa)

        binding.spKategori.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                strKategoriSelected = parent.getItemAtPosition(position).toString()
                strHargaSelected = strHarga[position]
                binding.spKategori.setEnabled(true)
                countHarga = strHargaSelected.toInt()

                updateTotalPrice()

                if (binding.inputBerat.getText().toString() != "") {
                    countBerat = binding.inputBerat.getText().toString().toInt()
                    setTotalPrice(countBerat)
                } else {
                    binding.inputHarga.setText(rupiahFormat(countHarga))
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })
//
        binding.inputBerat.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(editable: Editable) {
                binding.inputBerat.removeTextChangedListener(this)
                if (editable.isNotEmpty()) {
                    val berat = editable.toString().toIntOrNull() ?: 0 // Konversi ke Int atau gunakan nilai default jika tidak valid
                    setTotalPrice(berat)
                } else {
                    updateTotalPrice()
                }
                binding.inputBerat.addTextChangedListener(this)
            }
        })
//
        binding.inputTanggal.setOnClickListener { view: View? ->
            val tanggalJemput = Calendar.getInstance()
            val date =
                OnDateSetListener { view1: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    tanggalJemput[Calendar.YEAR] = year
                    tanggalJemput[Calendar.MONTH] = monthOfYear
                    tanggalJemput[Calendar.DAY_OF_MONTH] = dayOfMonth
                    val strFormatDefault = "d MMMM yyyy"
                    val simpleDateFormat = SimpleDateFormat(strFormatDefault, Locale.getDefault())
                    binding.inputTanggal.setText(simpleDateFormat.format(tanggalJemput.time))
                }
            DatePickerDialog(
                applicationContext, date,
                tanggalJemput[Calendar.YEAR],
                tanggalJemput[Calendar.MONTH],
                tanggalJemput[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }



    fun setTotalPrice(berat: Int) {
        countTotal = countHarga * berat
        binding.inputHarga.setText(rupiahFormat(countTotal))
    }
    //
    fun setInputData() {
        binding.btnCheckout.setOnClickListener { v: View? ->4
            val id = ref.push().key
            strNama = binding.inputNama.text.toString()
            strTanggal = binding.inputTanggal.text.toString()
            strAlamat = binding.inputAlamat.text.toString()
            strCatatan = binding.inputTambahan.text.toString()

            val dataInput = modelDatabase(id!! ,strNama, strKategoriSelected, countBerat, countHarga,strTanggal,
                strAlamat, strCatatan)

            if (strNama.isEmpty() or strTanggal.isEmpty() or strAlamat.isEmpty() or (strKategori.size == 0) or (countBerat == 0) or (countHarga == 0)) {
                ref.child(id).setValue(dataInput).addOnCompleteListener {
                    Toast.makeText(
                        applicationContext,
                        "Registrasi berhasil",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Data tidak boleh ada yang kosong!",
                    Toast.LENGTH_SHORT
                ).show()
//                inputDataViewModel.addDataSampah(
//                    strNama,
//                    strKategoriSelected,
//                    countBerat,
//                    countHarga,
//                    strTanggal,
//                    strAlamat,
//                    strCatatan
//
//                )
                Toast.makeText(
                    applicationContext,
                    "Pesanan Anda sedang diproses, cek di menu riwayat",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
    //
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

    fun updateTotalPrice() {
        val inputBeratText = binding.inputBerat.text
        if (inputBeratText?.isNotEmpty() == true) {
            val berat = binding.inputBerat.text.toString().toIntOrNull() ?: 0
            setTotalPrice(berat)
        } else {
            binding.inputHarga.setText(rupiahFormat(countHarga))
        }
    }

}