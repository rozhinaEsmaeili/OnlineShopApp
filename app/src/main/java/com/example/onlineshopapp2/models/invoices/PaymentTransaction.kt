package com.example.onlineshopapp2.models.invoices

import com.example.onlineshopapp2.models.customers.UserVM

data class PaymentTransaction(
    var items: List<InvoiceItem>?,
    var user: UserVM?
)
