package com.example.passwordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.passwordgenerator.activity.SignUpActivity

class MainActivity : AppCompatActivity() {

    var isChecked: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            if (isChecked) {
                isChecked = false
                val intent = Intent(this@MainActivity, SignUpActivity::class.java)
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