package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var signupEmailEditText : EditText
    private lateinit var signupPasswordEditText : EditText
    private lateinit var signupButton : Button
    private lateinit var alreadyRegisteredButton : TextView

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        init()
        listeners()
    }

    private fun listeners() {

        signupButton.setOnClickListener {

            val email = signupEmailEditText.text.toString()
            val password = signupPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty() || email.contains(" ") ||
                password.length < 8){
                Toast.makeText(this, "Does not meet password requirements", Toast.LENGTH_SHORT).show()
            }

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }

        alreadyRegisteredButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun init(){
        signupEmailEditText = findViewById(R.id.signupEmailEditText)
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText)
        signupButton = findViewById(R.id.signupButton)
        alreadyRegisteredButton = findViewById(R.id.alreadyRegisteredButton)
    }
}