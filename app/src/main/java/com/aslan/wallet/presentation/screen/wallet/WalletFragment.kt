package com.aslan.wallet.presentation.screen.wallet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aslan.wallet.R
import com.aslan.wallet.databinding.FragmentWalletBinding
import com.aslan.wallet.presentation.adapter.TransactionAdapter

class WalletFragment : Fragment() {

    private lateinit var viewModel: WalletViewModel
    private lateinit var adapter: TransactionAdapter

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TransactionAdapter(arrayListOf())
        viewModel = ViewModelProvider(requireActivity())[WalletViewModel::class.java]
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recentList.layoutManager = LinearLayoutManager(requireContext())
        binding.recentList.adapter = adapter

        binding.radioGroup.check(R.id.radioAll)
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radioAll ->
                    viewModel.loadData()
                R.id.radioIncome ->
                    loadIncome()
                R.id.radioExpense ->
                    loadExpense()
            }
        }



        viewModel.payment.observe(viewLifecycleOwner){pays ->
            pays?.let {
                adapter.updateTransaction(it.reversed())
                Log.d("pays: ", it.toString())
            }
        }

        getBalance()
        getIncomeBalance()
        getExpenseBalance()
    }

    private fun loadIncome(){
        viewModel.loadIncomeData()
        viewModel.incomePayments.observe(viewLifecycleOwner){
            it?.let {
                adapter.updateTransaction(it.reversed())
            }
        }
    }

    private fun loadExpense(){
        viewModel.loadExpenseData()
        viewModel.expensePayments.observe(viewLifecycleOwner){
            it?.let {
                adapter.updateTransaction(it.reversed())
            }
        }
    }

    private fun getBalance(){
        viewModel.loadBalance()
        viewModel.balance.observe(viewLifecycleOwner){
            it?.let {
                binding.walletBalance.amountView.walletAmount(it)
            }
        }
    }

    private fun getIncomeBalance(){
        viewModel.loadIncomeBalance()
        viewModel.incomeBalance.observe(viewLifecycleOwner){
            it?.let {
                binding.incomeAndExpenseUI.incomeAmount.walletAmount(it)
            }
        }
    }

    private fun getExpenseBalance(){
        viewModel.loadExpenseBalance()
        viewModel.expenseBalance.observe(viewLifecycleOwner){
            it?.let {
                binding.incomeAndExpenseUI.expenseAmount.walletAmount(it)
            }
        }
    }

}