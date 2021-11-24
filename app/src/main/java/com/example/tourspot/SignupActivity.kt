package com.example.tourspot

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = Firebase.auth


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
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }


}