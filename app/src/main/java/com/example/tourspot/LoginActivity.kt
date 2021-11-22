package com.example.tourspot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val jbtn = findViewById<Button>(R.id.join_btn)
        val lbtn = findViewById<Button>(R.id.login_btn)
        jbtn.setOnClickListener({
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        })
        lbtn.setOnClickListener({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}