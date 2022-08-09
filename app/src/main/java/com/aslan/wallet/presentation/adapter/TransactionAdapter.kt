package com.aslan.wallet.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aslan.wallet.R
import com.aslan.wallet.databinding.RecentTransactionItemBinding
import com.aslan.wallet.domain.model.Payment

class TransactionAdapter(private val transactions: ArrayList<Payment>): RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {
    class TransactionHolder(private val binding: RecentTransactionItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(payment: Payment){
            binding.transactionImg.load(payment.categoryIcon)
            binding.transactionTitle.text = payment.title
            binding.amountAZN.walletAmount(payment.amount, payment.typeColor)
            binding.transactionDate.setDate(payment.createdDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val binding = RecentTransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val pay = transactions[position]
        holder.onBind(pay)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTransaction(newTransactions: List<Payment>){
        transactions.clear()
        transactions.addAll(newTransactions)
        notifyDataSetChanged()
    }
}