package com.aslan.wallet.presentation.screen.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslan.wallet.domain.model.Payment
import com.aslan.wallet.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private var _payments: MutableLiveData<List<Payment>> = MutableLiveData()
    val payment: LiveData<List<Payment>> = _payments

    fun loadData(){
        viewModelScope.launch(IO) {
            _payments.postValue(useCases.getAllPaymentUseCase.invoke())
        }
    }

    private var _incomePayments: MutableLiveData<List<Payment>> = MutableLiveData()
    val incomePayments: LiveData<List<Payment>> = _incomePayments

    fun loadIncomeData(){
        viewModelScope.launch(IO) {
            _incomePayments.postValue(useCases.getAllPaymentByTypeUseCase.invoke("Income"))
        }
    }

    private var _expensePayments: MutableLiveData<List<Payment>> = MutableLiveData()
    val expensePayments: LiveData<List<Payment>> = _expensePayments

    fun loadExpenseData(){
        viewModelScope.launch(IO) {
            _expensePayments.postValue(useCases.getAllPaymentByTypeUseCase.invoke("Expense"))
        }
    }

    private var _balance: MutableLiveData<Double> = MutableLiveData()
    val balance: LiveData<Double> = _balance

    fun loadBalance(){
        viewModelScope.launch(IO) {
            val income = useCases.getWalletBalanceUseCase.invoke("Income")
            val expense = useCases.getWalletBalanceUseCase.invoke("Expense")
            val sum = income - expense
            _balance.postValue(sum)
        }
    }

    private var _incomeBalance: MutableLiveData<Double> = MutableLiveData()
    val incomeBalance: LiveData<Double> = _incomeBalance

    fun loadIncomeBalance(){
        viewModelScope.launch(IO) {
            val income = useCases.getWalletBalanceUseCase.invoke("Income")
            _incomeBalance.postValue(income)
        }
    }

    private var _expenseBalance: MutableLiveData<Double> = MutableLiveData()
    val expenseBalance: LiveData<Double> = _expenseBalance

    fun loadExpenseBalance(){
        viewModelScope.launch(IO) {
            val expense = useCases.getWalletBalanceUseCase.invoke("Expense")
            _expenseBalance.postValue(expense)
        }
    }

}