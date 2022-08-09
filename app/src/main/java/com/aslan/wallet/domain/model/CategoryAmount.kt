package com.aslan.wallet.domain.model

import androidx.room.Entity

@Entity
data class CategoryAmount(
    val name: String,

    val amount: Double
)