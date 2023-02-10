package com.example.passwordgenerator.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.passwordgenerator.databinding.ActivityLoginBinding

/**
 * Created by Rajdeep Sarkar on 09/02/2023
 * */

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var signUpPin: String
        binding.loginBtn.setOnClickListener {

            if (binding.pinStr.text.trim().isEmpty()) {
                Toast.makeText(
                    this@LoginActivity, "Please Enter PIN", Toast.LENGTH_SHORT
                ).show()
            } else {
                val sharedPreferences = EncryptedSharedPreferences.create(
                    "signinPinPreference",
                    masterKeyAlias,
                    applicationContext,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
                signUpPin = sharedPreferences.getString("signin_pin", "").toString()

                /**
                 * MASTER PIN TO LOGIN IS 0000
                 */

                if (binding.pinStr.text.toString() == signUpPin || binding.pinStr.text.toString() == "0000") {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@LoginActivity, "Please Enter Valid Pin ", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.movetosignupBtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}