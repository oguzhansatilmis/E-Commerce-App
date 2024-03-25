package com.android.ecommerceapp.ui.electronics

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.ecommerceapp.databinding.CategoryItemBinding
import com.android.ecommerceapp.model.ProductItem
import com.android.ecommerceapp.util.loadUrl

class CategoryAdapter(private val categoryList: List<ProductItem>) :RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {

        val categoryItem = categoryList[position]

        holder.binding.title.text = categoryItem.title
        holder.binding.price.text = (categoryItem.price.toString()+" $")
        holder.binding.image.loadUrl(categoryItem.image)

    }

    override fun getItemCount(): Int {
       return categoryList.size
    }
}