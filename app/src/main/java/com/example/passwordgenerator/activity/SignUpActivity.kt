package com.example.passwordgenerator.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.passwordgenerator.databinding.ActivitySignUpBinding


/**
 * Created by Rajdeep Sarkar on 09/02/2023
 * */

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val masterKey = MasterKey.Builder(this)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        binding.setpinBtn.setOnClickListener {

            if (binding.pinStr.text.toString().trim()
                    .isEmpty() || binding.pinStr.text.length != 4
            ) {
                Toast.makeText(
                    this@SignUpActivity, "Please Enter 4 Digit Pin!", Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@SignUpActivity, "Pin Has been Generated Successfully!", Toast.LENGTH_SHORT
                ).show()

                val sharedPreferences = EncryptedSharedPreferences.create(
                    applicationContext,
                    "signinPinPreference",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("signin_pin", binding.pinStr.text.toString())
                editor.apply()
            }
        }

        binding.movetologinBtn.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}