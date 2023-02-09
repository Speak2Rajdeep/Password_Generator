package com.example.passwordgenerator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordgenerator.activity.LoginActivity

/**
 * Created by Rajdeep Sarkar on 09/02/2023
 * */

class MainActivity : AppCompatActivity() {

    private var isChecked: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            if (isChecked) {
                isChecked = false
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000)
    }

    override fun onRestart() {
        super.onRestart()
        isChecked = true
    }
}