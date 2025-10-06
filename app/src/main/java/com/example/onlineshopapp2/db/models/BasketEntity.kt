package com.example.onlineshopapp2.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onlineshopapp2.models.products.ProductCategory
import com.example.onlineshopapp2.models.products.ProductColor
import com.example.onlineshopapp2.models.products.ProductSize

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var productId: Long,
    var quantity: Int,
    var sizeId: Long,
    var colorId: Long,
    var image: String?,
    var price: Long?,
    var title: String?,
    var hexColor: String,
    var size: String?
)