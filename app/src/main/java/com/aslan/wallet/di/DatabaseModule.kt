package com.aslan.wallet.di

import android.content.Context
import androidx.room.Room
import com.aslan.wallet.data.local.WalletDatabase
import com.aslan.wallet.data.repository.LocalDataSourceImpl
import com.aslan.wallet.domain.repository.LocalDataSource
import com.aslan.wallet.util.Constants.WALLET_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WalletDatabase{
        return Room.databaseBuilder(
            context,
            WalletDatabase::class.java,
            WALLET_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: WalletDatabase
    ): LocalDataSource{
        return LocalDataSourceImpl(database = database)
    }

}