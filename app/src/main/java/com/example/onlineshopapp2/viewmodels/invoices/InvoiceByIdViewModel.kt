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
class InvoiceByIdViewModel @Inject constructor(private val repository: InvoiceRepository) :
    ViewModel() {

    var token = ThisApp.token
    var invoiceId = ThisApp.invoiceId

    var data = mutableStateOf<Invoice?>(null)
    var isLoading = mutableStateOf(true)

    init {
        getInvoiceById(invoiceId) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                data.value = response.data!![0]
            }
        }

    }

    fun getInvoiceById(id: Long, onResponse: (response: ServiceResponse<Invoice>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getInvoiceById(id, token)
            onResponse(response)
        }
    }
}