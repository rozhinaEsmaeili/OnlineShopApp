package com.example.onlineshopapp2.api.products

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.ProductCategory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductCategoryApi {

    @GET("/api/productCategory")
    suspend fun getCategories(): ServiceResponse<ProductCategory>

    @GET("/api/productCategory/{id}")
    suspend fun getCategoryById(@Path("id") id: Long): ServiceResponse<ProductCategory>
}