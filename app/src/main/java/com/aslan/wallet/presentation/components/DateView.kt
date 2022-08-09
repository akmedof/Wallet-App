package com.aslan.wallet.presentation.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.aslan.wallet.databinding.DateComponentBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateView(context: Context, attr: AttributeSet?): LinearLayout(context, attr) {

    private var _binding: DateComponentBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = DateComponentBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    @SuppressLint("NewApi")
    fun setDate(date: String){
        val d = LocalDateTime.parse(date)
        val year = DateTimeFormatter.ofPattern("yyyy").format(d)
        val month = DateTimeFormatter.ofPattern("MMM").format(d)
        val day = DateTimeFormatter.ofPattern("dd").format(d)
        val hour = DateTimeFormatter.ofPattern("HH").format(d)
        val minute = DateTimeFormatter.ofPattern("mm").format(d)
        binding.dateTimeHour.text = hour
        binding.dateTimeMinute.text = minute
        binding.dateTimeDay.text = day
        binding.dateTimeMonth.text = month
        binding.dateTimeYear.text = year
    }

}