package com.aslan.wallet.domain.usecase

import com.aslan.wallet.domain.usecase.add_payment.AddPaymentUseCase
import com.aslan.wallet.domain.usecase.get_all_category.GetAllCategoryUseCase
import com.aslan.wallet.domain.usecase.get_all_payment.GetAllPaymentByTypeUseCase
import com.aslan.wallet.domain.usecase.get_all_payment.GetAllPaymentUseCase
import com.aslan.wallet.domain.usecase.wallet_balance.GetWalletBalanceUseCase

class UseCases(
    val getAllPaymentUseCase: GetAllPaymentUseCase,
    val addPaymentUseCase: AddPaymentUseCase,
    val getWalletBalanceUseCase: GetWalletBalanceUseCase,
    val getAllPaymentByTypeUseCase: GetAllPaymentByTypeUseCase,
    val getAllCategoryUseCase: GetAllCategoryUseCase
)
