package com.example.onlineshopapp2.api.products

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ColorApi {

    @GET("/api/color")
    suspend fun getcolors(): ServiceResponse<ProductColor>

    @GET("/api/color/{id}")
    suspend fun getcolorById(@Path("id") id: Long): ServiceResponse<ProductColor>
}