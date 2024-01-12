    package com.example.uas_mobile.activity


    import android.content.Intent
    import android.os.Bundle
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.example.uas_mobile.databinding.ActivityRegisterBinding
    import com.example.uas_mobile.database.User
    import com.google.firebase.database.DatabaseReference
    import com.google.firebase.database.FirebaseDatabase


    class ActivityRegister : AppCompatActivity() {

        private lateinit var binding: ActivityRegisterBinding
        private lateinit var ref: DatabaseReference


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)

            ref = FirebaseDatabase.getInstance().getReference("user")

            binding.buttonRegister.setOnClickListener {
                val id = ref.push().key
                val email = binding.editTextRegisterEmail.text.toString()
                val address = binding.editTextRegisterAddress.text.toString()
                val username = binding.editTextRegisterUsername.text.toString()
                val password = binding.editTextRegisterPassword.text.toString()
                val konfirmasiPass = binding.editTextRegisterKonfirmasiPassword.text.toString()

                val user = User(id!! ,username, address, email, password)

                if (email.isNotEmpty() && address.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()
                    && konfirmasiPass.isNotEmpty()
                ) {
                    if (password == konfirmasiPass) {
                        ref.child(id).setValue(user).addOnCompleteListener {
                            Toast.makeText(
                                applicationContext,
                                "Registrasi berhasil",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            val intent = Intent(this, ActivityLoginUser::class.java)
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }