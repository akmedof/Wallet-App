package com.aslan.wallet.presentation.screen.report

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aslan.wallet.R
import com.aslan.wallet.databinding.FragmentReportBinding
import com.aslan.wallet.domain.model.Category
import com.aslan.wallet.domain.model.Payment
import java.time.LocalDateTime

class ReportFragment : Fragment() {

    private lateinit var viewModel: ReportViewModel

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ReportViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = listOf(
            Category("Car", R.drawable.car_icon),
            Category("Food", R.drawable.food_icon),
            Category("Market", R.drawable.market_icon),
            Category("Travel", R.drawable.travel_icon),
            Category("Game", R.drawable.game_icon),
            Category("Restaurant", R.drawable.restaurant_icon),
            Category("Debit", R.drawable.debit_icon),
            Category("Sport", R.drawable.sports_icon),
            Category("Debit", R.drawable.debit_icon),
            Category("Business", R.drawable.business_icon),
            Category("Work", R.drawable.work_icon),
            Category("Other", R.drawable.other_icon)
        )

        val cList = listOf(
            "Car",
            "Food",
            "Business",
            "Work",
            "Market",
            "Travel",
            "Game",
            "Restaurant",
            "Debit",
            "Sport",
            "Other"
        )
        val array = ArrayAdapter(requireContext(), R.layout.dropdown_item, cList.toList())
        binding.autoCompleteCategory.setAdapter(array)

        var type = ""
        var color = "#FFD61C"

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioExpense -> {
                    type = binding.radioExpense.text.toString()
                    color = "#F5F10655"
                    Toast.makeText(
                        requireContext(),
                        binding.radioExpense.text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.radioIncome -> {
                    type = binding.radioIncome.text.toString()
                    color = "#31AA36"
                    Toast.makeText(
                        requireContext(),
                        binding.radioIncome.text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


        binding.addWallet.setOnClickListener { click ->
            if (type.isNotEmpty()) {
                val title = validTitle()
                val money = validAmount()
                val desc = validDescription()
                val category = binding.autoCompleteCategory.text.toString()
                val created = LocalDateTime.now()
                var cType: Int? = null
                categories.map { c ->
                    if (c.name == binding.autoCompleteCategory.text.toString()) {
                        cType = c.icon
                    }
                }


                val p = Payment(
                    id = 0,
                    title = title,
                    amount = money,
                    type = type,
                    typeColor = color,
                    categoryName = category,
                    categoryIcon = cType!!,
                    createdDate = created.toString(),
                    descriptions = desc
                )
                if (money > 0) {
                    viewModel.addPayment(p)
                    Navigation.findNavController(click)
                        .navigate(ReportFragmentDirections.actionReportFragmentToWalletFragment())
                } else Toast.makeText(requireContext(), "Select Pay amount.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Select Payment type.", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun validTitle(): String {
        var title: String = ""
        title = if (binding.titleEditText.text!!.isNotEmpty())
            binding.titleEditText.text.toString()
        else "#diger"
        return title
    }

    private fun validAmount(): Double {
        var amount: Double = 0.0
        amount = if (binding.moneyEditText.text!!.isNotEmpty())
            binding.moneyEditText.text.toString().toDouble()
        else 0.0
        return amount
    }

    private fun validDescription(): String {
        var desc: String = ""
        desc = if (binding.descEditText.text!!.isNotEmpty())
            binding.descEditText.text.toString()
        else "#qeydolunmayib"
        return desc
    }
}