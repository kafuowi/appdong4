package com.example.tourspot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val btn = findViewById<Button>(R.id.signup_button)
        val authemail = findViewById<EditText>(R.id.signup_email)
        val authpw = findViewById<EditText>(R.id.signup_password)
        val authpwch = findViewById<EditText>(R.id.signup_password_check)
        var password = ""
        var email = ""

        btn.setOnClickListener {
            email = authemail.text.toString()
            password = authpw.text.toString()
            var passwordCheck = authpwch.text.toString()
            if(password.equals(passwordCheck)){
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
        }
    }
}