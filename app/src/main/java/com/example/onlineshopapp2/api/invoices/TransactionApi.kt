package com.example.onlineshopapp2.api.invoices

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.invoices.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {

    @POST("/api/trx/gotoPayment")
    suspend fun gotoPayment(
        @Body data: PaymentTransaction
    ): ServiceResponse<String>
}