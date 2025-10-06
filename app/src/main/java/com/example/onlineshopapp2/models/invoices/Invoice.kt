package com.example.onlineshopapp2.models.invoices

import com.example.onlineshopapp2.models.customers.User

data class Invoice(
    var addDate: String?,
    var id: Long?,
    var items: List<InvoiceItem>?,
    var paymentDate: String?,
    var status: String?,
    var user: User?
)