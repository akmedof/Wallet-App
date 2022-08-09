package com.aslan.wallet.domain.model

data class Category(
    val name: String,
    val icon: Int,
    val count: Int? = 0,
    val amount: Double? = 0.0
)
