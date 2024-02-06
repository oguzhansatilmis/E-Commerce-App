package com.android.ecommerceapp.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.ecommerceapp.databinding.ProductItemBinding
import com.android.ecommerceapp.model.Product
import com.android.ecommerceapp.util.loadUrl
import com.bumptech.glide.Glide
import javax.inject.Inject

class ProductAdapter( val productList :List<Product>) :RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

   class ViewHolder(val binding:ProductItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productURL = productList[position].image

        holder.binding.productPrice.text= productList[position].price.toString() +" $"
        holder.binding.productTitle.text = productList[position].title
        holder.binding.productImage.loadUrl(productURL)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}