package com.aslan.wallet.domain.usecase.add_payment

import com.aslan.wallet.data.repository.Repository
import com.aslan.wallet.domain.model.Payment

class AddPaymentUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(payment: Payment){
        repository.addPayment(payment)
    }
}