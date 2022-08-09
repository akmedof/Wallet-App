package com.aslan.wallet.data.repository

import com.aslan.wallet.data.local.WalletDatabase
import com.aslan.wallet.domain.model.Category
import com.aslan.wallet.domain.model.Payment
import com.aslan.wallet.domain.repository.LocalDataSource

class LocalDataSourceImpl(database: WalletDatabase): LocalDataSource {

    private val dao = database.paymentDao()

    override suspend fun addPayment(payment: Payment) {
        return dao.insertPayment(payment)
    }

    override suspend fun getAllPayment(): List<Payment> {
        return dao.getAllPayment()
    }

    override suspend fun getWalletBalanceByType(type: String): Double {
        return dao.getWalletBalanceByType(type)
    }

    override suspend fun getAllPaymentByType(type: String): List<Payment> {
        return dao.getAllPaymentByType(type)
    }

    override suspend fun getAllCategory(): List<Category> {
        return dao.getAllCategory()
    }
}