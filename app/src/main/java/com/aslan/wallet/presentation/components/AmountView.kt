package com.aslan.wallet.presentation.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.aslan.wallet.R
import com.aslan.wallet.databinding.AmountComponentBinding

@SuppressLint("Recycle")
class AmountView(context: Context, attr: AttributeSet?): LinearLayout(context, attr) {

    private var _binding : AmountComponentBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = AmountComponentBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        attr?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.AmountView)
//            val amountCurrencySize = typedArray.getString(R.styleable.AmountView_amountCurrencySize)
            val amountCurrencyColor = typedArray.getString(R.styleable.AmountView_amountColor)
            val amountSize = typedArray.getString(R.styleable.AmountView_amountSize)
            Log.d("acolor", amountCurrencyColor.toString())
            val size = amountSize!!.toFloat()
            binding.amountAZN.textSize = ((size/4)*3)
            binding.amountAZN.setTextColor(Color.parseColor(amountCurrencyColor))
            binding.amountDecimal.textSize = size
            binding.amountDecimal.setTextColor(Color.parseColor(amountCurrencyColor))
            binding.amountSingularity.textSize = size
            binding.amountSingularity.setTextColor(Color.parseColor(amountCurrencyColor))
            binding.amountPoint.textSize = size
            binding.amountPoint.setTextColor(Color.parseColor(amountCurrencyColor))
        }
    }

    fun walletAmount(amount: Double){
        if (!amount.isNaN()){
            val a = String.format("%.2f", amount)
            val separate = a.toString().split(",")
            binding.amountSingularity.text = separate[0]
            binding.amountDecimal.text = separate[1]
        }else{
            binding.amountSingularity.text = "0"
            binding.amountDecimal.text = "0"
        }
    }

    fun walletAmount(amount: Double, color: String){
        if (!amount.isNaN()){
            val a = String.format("%.2f", amount)
            val separate = a.toString().split(",")
            binding.amountSingularity.text = separate[0]
            binding.amountDecimal.text = separate[1]
        }else{
            binding.amountSingularity.text = "0"
            binding.amountDecimal.text = "0"
        }
        walletAmountColor(color)
    }

    private fun walletAmountColor(color: String){
        binding.amountAZN.setTextColor(Color.parseColor(color))
        binding.amountDecimal.setTextColor(Color.parseColor(color))
        binding.amountSingularity.setTextColor(Color.parseColor(color))
        binding.amountPoint.setTextColor(Color.parseColor(color))
    }

}