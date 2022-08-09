package com.aslan.wallet.domain.usecase.get_all_payment

import com.aslan.wallet.data.repository.Repository
import com.aslan.wallet.domain.model.Payment

class GetAllPaymentByTypeUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(type: String): List<Payment>{
        return repository.getAllPaymentByType(type)
    }
}