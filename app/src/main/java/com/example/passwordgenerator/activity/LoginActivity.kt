package com.example.passwordgenerator.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordgenerator.databinding.ActivityLoginBinding

/**
 * Created by Rajdeep Sarkar on 09/02/2023
 * */

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var signUpPin: String
        binding.loginBtn.setOnClickListener {

            if (binding.pinStr.text.trim().isEmpty()) {
                Toast.makeText(
                    this@LoginActivity, "Enter Proper Credentials", Toast.LENGTH_SHORT
                ).show()
            } else {
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(
                    "signinPinPreference", Context.MODE_PRIVATE
                )
                signUpPin = sharedPreferences.getString("signin_pin", "").toString()

                if (binding.pinStr.text.toString() != signUpPin) {
                    Toast.makeText(
                        this@LoginActivity, "Please Enter Valid Pin ", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    /*Toast.makeText(
                        this@LoginActivity, "Login Success", Toast.LENGTH_SHORT
                    ).show()*/
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        binding.movetosignupBtn.setOnClickListener {
            Toast.makeText(
                this@LoginActivity, "Moving to Sign Up page", Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}