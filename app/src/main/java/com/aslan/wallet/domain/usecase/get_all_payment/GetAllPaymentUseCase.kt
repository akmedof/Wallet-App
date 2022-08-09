package com.aslan.wallet.domain.usecase.get_all_payment

import androidx.lifecycle.LiveData
import com.aslan.wallet.data.repository.Repository
import com.aslan.wallet.domain.model.Payment

class GetAllPaymentUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(): List<Payment>{
        return repository.payments()
    }

}