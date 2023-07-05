package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var forgotButton : Button
    private lateinit var forgotEmail : EditText
    private lateinit var backLoginTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        init()
        listeners()
    }

    fun listeners(){
        forgotButton.setOnClickListener {
            val email = forgotEmail.text.toString()
            if(email.isEmpty()){
                Toast.makeText(this, "Email is empty, please fill it", Toast.LENGTH_SHORT).show()
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this, "Email sent successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        backLoginTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun init(){
        forgotButton = findViewById(R.id.forgotButton)
        forgotEmail = findViewById(R.id.forgotEmailEditText)
        backLoginTextView = findViewById(R.id.backLoginTextView)
    }
}