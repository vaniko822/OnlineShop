package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.example.onlineshop.databinding.ActivityAddProductBinding
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddProductBinding
    private lateinit var db : DatabaseReference
    private lateinit var backToProductTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addProductButton.setOnClickListener() {

            val carName = binding.nameEditText.text.toString()
            val carPrice = binding.priceEditText.text.toString()
            val carYear = binding.yearEditText.text.toString()
            val carPictureUrl = binding.carPictureUrlEditText.text.toString()
            val carid = Random.nextInt();

            if(!carName.isEmpty() || !carPrice.isNullOrEmpty() || !carYear.isNullOrEmpty()){
                db = FirebaseDatabase.getInstance().getReference("Products")
                val product = Product(carid, carName, carPictureUrl, carPrice.toInt(), carYear.toInt());

                db.child(carid.toString()).setValue(product).addOnCompleteListener(){

                    binding.nameEditText.text.clear()
                    binding.priceEditText.text.clear()
                    binding.yearEditText.text.clear()
                    binding.carPictureUrlEditText.text.clear()

                    Toast.makeText(this,"Successfully Added", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener(){

                    Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()

                }

                startActivity(Intent(this, ProductsActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please fill empty fields", Toast.LENGTH_SHORT).show()
            }
        }

        init()
        listeners()
    }

    private fun listeners(){
        backToProductTextView.setOnClickListener {
            startActivity(Intent(this, ProductsActivity::class.java))
            finish()
        }
    }

    private fun init() {
        backToProductTextView = findViewById(R.id.backToProductTextView)
    }
}