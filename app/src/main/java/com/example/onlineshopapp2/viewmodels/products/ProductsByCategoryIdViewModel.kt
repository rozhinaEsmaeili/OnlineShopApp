package com.example.onlineshopapp2.viewmodels.products

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.Product
import com.example.onlineshopapp2.repositories.products.ProductRepository
import com.example.onlineshopapp2.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsByCategoryIdViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    var pageIndex = mutableIntStateOf(0)
    var pageSize = 3
    var categoryId: Long = ThisApp.categoryId

    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)
    private var scrollPosition = 0


    init {
        getProductCategoryById(pageIndex.intValue, pageSize, categoryId) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }


    fun getProductCategoryById(
        pageIndex: Int,
        pageSize: Int,
        categoryId: Long,
        onResponse: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getProductByCategoryId(categoryId, pageIndex, pageSize)
            onResponse(response)
        }
    }

    fun incrementPageIndex() {
        pageIndex.intValue = pageIndex.intValue + 1
    }

    fun onScrollPosition(position: Int) {
        scrollPosition = position
    }

    fun appendItems(items: List<Product>) {

        val current = ArrayList(dataList.value)
        current.addAll(items)
        dataList.value = current
    }

    fun nextPage() {
        viewModelScope.launch {
            if (scrollPosition + 1 >= pageIndex.intValue * pageSize) {
                isLoading.value = true
                incrementPageIndex()

                if (pageIndex.intValue > 0) {
                    getProductCategoryById(pageIndex.intValue, pageSize, categoryId) { response ->
                        if (response.status == "OK") {
                            appendItems(response.data!!)
                        }
                        isLoading.value = false
                    }
                }
            }
        }
    }
}