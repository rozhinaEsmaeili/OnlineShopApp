package com.example.onlineshopapp2.models.products

data class Product(
    var addDate: String? = null,
    var category: ProductCategory? = null,
    var colors: List<ProductColor>? = null,
    var description: String? = null,
    var id: Long? = null,
    var image: String? = null,
    var price: Long? = 0,
    var sizes: List<ProductSize>? = null,
    var title: String? = null,
    var visitCount: Int? = 0
)
