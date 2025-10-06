package com.example.onlineshopapp2.viewmodels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.Product
import com.example.onlineshopapp2.repositories.products.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)
    var productDeatails = mutableStateOf<Product?>(null)

    init {
        getProducts(pageIndex = 0, pageSize = 6) { response ->
            isLoading.value = false
            if (response.status == "OK".uppercase()) {
                dataList.value = response.data!!
            }
        }
    }

    fun getProducts(
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getProducts(pageIndex, pageSize)
            onResponse(response)
        }
    }

    fun getProductById(id: Long, onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getProductById(id)
            onResponse(response)
        }
    }

    fun getProductCategoryById(
        pageIndex: Int,
        pageSize: Int,
        id: Long,
        onResponse: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getProductByCategoryId(id, pageIndex, pageSize)
            onResponse(response)
        }
    }

    fun getNewProducts(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getNewProducts()
            dataList.value = response.data!!
            onResponse(response)
        }
    }

    fun getPopularProducts(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getPopularProducts()
            dataList.value = response.data!!
            onResponse(response)
        }
    }

}