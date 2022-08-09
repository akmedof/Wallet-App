package com.aslan.wallet.domain.usecase.wallet_balance

import com.aslan.wallet.data.repository.Repository

class GetWalletBalanceUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(type: String):
            Double = repository.getWalletBalanceByType(type)

}