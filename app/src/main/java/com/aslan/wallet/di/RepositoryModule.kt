package com.aslan.wallet.di

import com.aslan.wallet.data.repository.Repository
import com.aslan.wallet.domain.usecase.UseCases
import com.aslan.wallet.domain.usecase.add_payment.AddPaymentUseCase
import com.aslan.wallet.domain.usecase.get_all_category.GetAllCategoryUseCase
import com.aslan.wallet.domain.usecase.get_all_payment.GetAllPaymentByTypeUseCase
import com.aslan.wallet.domain.usecase.get_all_payment.GetAllPaymentUseCase
import com.aslan.wallet.domain.usecase.wallet_balance.GetWalletBalanceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UseCases {
        return UseCases(
            getAllPaymentUseCase = GetAllPaymentUseCase(repository),
            addPaymentUseCase = AddPaymentUseCase(repository),
            getWalletBalanceUseCase = GetWalletBalanceUseCase(repository),
            getAllPaymentByTypeUseCase = GetAllPaymentByTypeUseCase(repository),
            getAllCategoryUseCase = GetAllCategoryUseCase(repository)
        )
    }


}