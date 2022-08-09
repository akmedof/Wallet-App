package com.aslan.wallet.domain.usecase.get_all_category

import com.aslan.wallet.data.repository.Repository
import com.aslan.wallet.domain.model.Category

class GetAllCategoryUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(): List<Category> = repository.getAllCategory()

}