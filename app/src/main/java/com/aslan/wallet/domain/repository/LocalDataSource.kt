package com.aslan.wallet.domain.repository

import com.aslan.wallet.domain.model.Category
import com.aslan.wallet.domain.model.Payment

interface LocalDataSource {

    suspend fun addPayment(payment: Payment)
    suspend fun getAllPayment(): List<Payment>
    suspend fun getWalletBalanceByType(type: String): Double
    suspend fun getAllPaymentByType(type: String): List<Payment>
    suspend fun getAllCategory(): List<Category>

}