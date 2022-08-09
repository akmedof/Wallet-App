package com.aslan.wallet.presentation.screen.analytics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslan.wallet.domain.model.Category
import com.aslan.wallet.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private var _categories: MutableLiveData<List<Category>> = MutableLiveData()
    var categories: LiveData<List<Category>> = _categories

    fun loadData(){
        viewModelScope.launch(IO) {
            _categories.postValue(useCases.getAllCategoryUseCase.invoke())
        }
    }

}