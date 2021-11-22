package com.example.tourspot

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val btn = findViewById<Button>(R.id.signin_button)
        val authemail = findViewById<EditText>(R.id.signin_email)
        var password = 0
        var email = ""

        btn.setOnClickListener {
            email = authemail.text.toString()
        }
    }
}