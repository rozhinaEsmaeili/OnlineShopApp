package com.example.onlineshopapp2.repositories.invoices

import com.example.onlineshopapp2.api.invoices.InvoiceApi
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.invoices.Invoice
import com.example.onlineshopapp2.repositories.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class InvoiceRepository @Inject constructor(private val api: InvoiceApi) : BaseRepository() {

    suspend fun getInvoiceById(id: Long, token: String): ServiceResponse<Invoice> {
        return try {
            api.getInvocieById(id, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getAllByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int,
        token: String
    ): ServiceResponse<Invoice> {
        return try {
            api.getAllByUserId(userId, pageIndex, pageSize, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun addInvoice(invoice: Invoice, token: String): ServiceResponse<Invoice> {
        return try {
            api.addInvoice(invoice, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

}