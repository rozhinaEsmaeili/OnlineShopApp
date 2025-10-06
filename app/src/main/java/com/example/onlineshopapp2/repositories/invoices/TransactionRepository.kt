package com.example.onlineshopapp2.repositories.invoices

import com.example.onlineshopapp2.api.invoices.TransactionApi
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.invoices.PaymentTransaction
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(private val api: TransactionApi) {

    suspend fun gotoPayment(data: PaymentTransaction): ServiceResponse<String> {
        return try {
            api.gotoPayment(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}