package com.dtunctuncer.product.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dtunctuncer.product.R
import com.dtunctuncer.product.db.entitiy.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val listener: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var products = emptyList<Product>()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(view: View, private val listener: (Product) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            with(product) {
                Picasso.with(itemView.context).cancelRequest(itemView.image)
                Picasso.with(itemView.context).load(product.image).into(itemView.image)
                itemView.name.text = product.name
                itemView.price.text = product.price.toString()
                itemView.setOnClickListener { listener.invoke(product) }
            }
        }
    }

    fun setList(productList: List<Product>) {
        products = productList
        notifyDataSetChanged()
    }
}