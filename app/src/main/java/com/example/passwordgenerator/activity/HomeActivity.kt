package com.example.passwordgenerator.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.passwordgenerator.R
import com.example.passwordgenerator.databinding.ActivityHomeBinding
import com.example.passwordgenerator.databinding.ActivityLoginBinding

/**
 * Created by Rajdeep Sarkar on 09/02/2023
 * */


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var str3 : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generatepasswordBtn.setOnClickListener {
            val sharedPreferences: SharedPreferences = this.getSharedPreferences(
                "oldPasswordPreference", Context.MODE_PRIVATE
            )
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("old_password", binding.newPassword.text.toString())
            editor.apply()

            binding.oldPassword.text = sharedPreferences.getString("old_password", "").toString()
            binding.newPassword.text = test()
        }

        /*binding.newPassword.setOnClickListener {
            val sharedPreferences: SharedPreferences = this.getSharedPreferences(
                "oldPasswordPreference", Context.MODE_PRIVATE
            )
              sharedPreferences.getString("old_password", "").toString()
        }*/
    }

    fun test() : String{
        val str1 = "Rajdeep"
        val str2 = "Anik"
        str3 = str1 + str2
        return str3
    }
}