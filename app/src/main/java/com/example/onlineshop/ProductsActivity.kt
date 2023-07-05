package com.example.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ProductsActivity : AppCompatActivity() {

    private lateinit var db : DatabaseReference
    private lateinit var productRecycleView : RecyclerView
    private lateinit var productArrayList : ArrayList<Product>
    private lateinit var addProductButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productRecycleView = findViewById(R.id.productsRecyclerView)
        productRecycleView.layoutManager = LinearLayoutManager(this)
        productRecycleView.setHasFixedSize(true)

        productArrayList = arrayListOf<Product>()
        GetProductData()

        init()
        listeners()
    }


    private fun GetProductData(){
        db = FirebaseDatabase.getInstance().getReference("Products")

        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(productSnapshot in snapshot.children){
                        val product = productSnapshot.getValue(Product::class.java)
                        productArrayList.add(product!!)
                    }

                    productRecycleView.adapter = MyAdapter(productArrayList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun listeners(){
        addProductButton.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
            finish()
        }
    }

    private fun init() {
        addProductButton = findViewById(R.id.addProductButton)
    }
}