package com.example.onlineshopapp2.viewmodels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.ProductCategory
import com.example.onlineshopapp2.repositories.products.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(private val repository: ProductCategoryRepository) :
    ViewModel() {

    var dataList = mutableStateOf<List<ProductCategory>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getCategories { response ->
            isLoading.value = false
            if (response.status == "OK".uppercase()) {
                dataList.value = response.data!!
            }
        }
    }

    fun getCategories(onResponse: (response: ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getCategories()
            onResponse(response)
        }
    }

    fun getCategoryById(
        id: Long,
        onResponse: (response: ServiceResponse<ProductCategory>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getCategoryById(id)
            onResponse(response)
        }
    }
}