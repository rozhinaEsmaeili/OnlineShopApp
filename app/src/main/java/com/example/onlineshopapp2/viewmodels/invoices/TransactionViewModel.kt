package com.example.onlineshopapp2.viewmodels.Transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.invoices.PaymentTransaction
import com.example.onlineshopapp2.repositories.invoices.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val repository: TransactionRepository) :
    ViewModel() {

    fun goToPayment(
        data: PaymentTransaction,
        onResponse: (response: ServiceResponse<String>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.gotoPayment(data)
            onResponse(response)
        }
    }

}