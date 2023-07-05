package com.example.onlineshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val productList: ArrayList<Product>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent,
            false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = productList[position]

        holder.name.text = currentitem.name
        Glide.with(holder.itemView.context)
            .load(currentitem.pictureurl)
            .into(holder.pictureurl)
        holder.price.text = currentitem.price.toString() + "$"
        holder.year.text = currentitem.year.toString()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.productNameTextView)
        val pictureurl : ImageView = itemView.findViewById(R.id.productimageView)
        val price : TextView = itemView.findViewById(R.id.priceTextView)
        val year : TextView = itemView.findViewById(R.id.yearTextView)
    }
}