package com.example.onlineshopapp2.models.invoices

import com.example.onlineshopapp2.db.models.BasketEntity
import com.example.onlineshopapp2.models.products.Product

data class InvoiceItem(
    var id: Long? = null,
    var product: Product?,
    var quantity: Int?,
    var unitPrice: Long? = null

) {
    companion object {
        fun convertToInvoiceItems(basketEntity: BasketEntity): InvoiceItem {
            return InvoiceItem(
                product = Product(id = basketEntity.productId),
                quantity = basketEntity.quantity
            )
        }
    }
}
