package com.aslan.wallet.data.repository

import com.aslan.wallet.domain.model.Category
import com.aslan.wallet.domain.model.Payment
import com.aslan.wallet.domain.repository.LocalDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource
) {

    suspend fun payments(): List<Payment>{
        return local.getAllPayment()
    }

    suspend fun addPayment(payment: Payment){
        local.addPayment(payment)
    }

    suspend fun getWalletBalanceByType(type: String):
            Double = local.getWalletBalanceByType(type)

    suspend fun getAllPaymentByType(type: String):
            List<Payment> = local.getAllPaymentByType(type)

    suspend fun getAllCategory():
            List<Category> = local.getAllCategory()
}