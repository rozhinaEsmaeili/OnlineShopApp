package com.example.onlineshopapp2.api.invoices

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.invoices.Invoice
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {

    @GET("/api/invoice/{id}")
    suspend fun getInvocieById(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    ): ServiceResponse<Invoice>

    @GET("/api/invoice/user/{userId}")
    suspend fun getAllByUserId(
        @Path("userId") userId: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") token: String
    ): ServiceResponse<Invoice>

    @POST("/api/invoice")
    suspend fun addInvoice(
        @Body invoice: Invoice,
        @Header("Authorization") token: String
    ): ServiceResponse<Invoice>

}