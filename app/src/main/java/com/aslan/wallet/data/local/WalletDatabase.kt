package com.aslan.wallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aslan.wallet.domain.model.Payment

@Database(entities = [Payment::class], version = 1 , exportSchema = false)
abstract class WalletDatabase: RoomDatabase() {

    abstract fun paymentDao(): PaymentDao

}