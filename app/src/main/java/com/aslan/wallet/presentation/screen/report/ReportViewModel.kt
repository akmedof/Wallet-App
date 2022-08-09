package com.aslan.wallet.presentation.screen.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslan.wallet.domain.model.Payment
import com.aslan.wallet.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val useCase: UseCases
): ViewModel() {

    fun addPayment(payment: Payment){
        viewModelScope.launch(IO) {
            useCase.addPaymentUseCase.invoke(payment)
        }
    }

}