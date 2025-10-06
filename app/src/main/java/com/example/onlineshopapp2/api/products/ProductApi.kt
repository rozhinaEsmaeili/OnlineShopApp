package com.example.onlineshopapp2.api.products

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("/api/product")
    suspend fun getProducts(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ServiceResponse<Product>

    @GET("/api/product/{id}")
    suspend fun getProductById(@Path("id") id: Long): ServiceResponse<Product>

    @GET("/api/product/cat/{id}")
    suspend fun getProductByCategoryId(
        @Path("id") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ServiceResponse<Product>

    @GET("/api/product/new")
    suspend fun getNewProducts(): ServiceResponse<Product>

    @GET("/api/product/popular")
    suspend fun getPopularProducts(): ServiceResponse<Product>
}