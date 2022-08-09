package com.aslan.wallet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aslan.wallet.util.Constants.PAYMENT_TABLE

@Entity(tableName = PAYMENT_TABLE)
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val amount: Double,
    val type: String,
    val typeColor: String,
    val categoryName: String,
    val categoryIcon: Int,
    val descriptions: String,
    val createdDate: String
)
