package com.aslan.wallet.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aslan.wallet.databinding.CategoryItemBinding
import com.aslan.wallet.domain.model.Category

class CategoryAdapter(private val categories: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    class CategoryHolder(private val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: Category){
            binding.categoryImg.setImageResource(category.icon)
            binding.categoryName.text = category.name
            binding.categoryPaymentCount.text = category.count.toString()
            category.amount?.let { binding.categoryAmount.walletAmount(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val pay = categories[position]
        holder.onBind(pay)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategories(newList: List<Category>){
        categories.clear()
        categories.addAll(newList)
        notifyDataSetChanged()
    }
}