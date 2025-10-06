package com.example.onlineshopapp2.viewmodels.invoices

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.invoices.Invoice
import com.example.onlineshopapp2.repositories.invoices.InvoiceRepository
import com.example.onlineshopapp2.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(private val repository: InvoiceRepository) :
    ViewModel() {

    var token = ThisApp.token
    var userId = ThisApp.userId
    var pageIndex = mutableIntStateOf(0)
    var pageSize = 3
    private var scrollPosition = 0

    var dataList = mutableStateOf<List<Invoice>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getAllByUserId(userId, pageIndex.intValue, pageSize) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }

    }

    fun addInvoice(invoice: Invoice, onResponse: (response: ServiceResponse<Invoice>) -> Unit) {
        viewModelScope.launch {
            var response = repository.addInvoice(invoice, token)
            onResponse(response)
        }
    }

    fun getInvoiceById(id: Long, onResponse: (response: ServiceResponse<Invoice>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getInvoiceById(id, token)
            onResponse(response)
        }
    }

    fun getAllByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Invoice>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getAllByUserId(userId, pageIndex, pageSize, token)
            onResponse(response)
        }
    }

    fun incrementPageIndex() {
        pageIndex.intValue = pageIndex.intValue + 1
    }

    fun onScrollPositionChange(position: Int) {
        scrollPosition = position
    }

    fun appendItems(items: List<Invoice>) {
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
                    getAllByUserId(userId, pageIndex.intValue, pageSize) { response ->
                        if (response.status == "OK" && response.data!!.isNotEmpty()) {
                            appendItems(response.data!!)
                        }
                        isLoading.value = false
                    }
                }
            }
        }
    }
}