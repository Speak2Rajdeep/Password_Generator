package com.example.passwordgenerator.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordgenerator.databinding.ActivityHomeBinding

/**
 * Created by Rajdeep Sarkar on 09/02/2023
 * */

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var str3: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            "oldPasswordPreference", Context.MODE_PRIVATE
        )
        binding.oldPassword.text = sharedPreferences.getString("old_password", "").toString()
        binding.newPassword.text = sharedPreferences.getString("new_password", "").toString()

        binding.generatepasswordBtn.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("old_password", binding.newPassword.text.toString())

            binding.oldPassword.text = sharedPreferences.getString("old_password", "").toString()
            binding.newPassword.text = test()
            editor.putString("new_password", binding.newPassword.text.toString())
            editor.apply()
        }
    }

    fun test(): String {
        val str1 = "Rajdeep"
        val str2 = "Anik"
        str3 = str1 + str2
        return str3
    }
}