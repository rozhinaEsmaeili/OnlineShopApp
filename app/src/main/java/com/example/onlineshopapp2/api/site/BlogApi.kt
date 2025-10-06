package com.example.onlineshopapp2.api.site

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.site.Blog
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogApi {

    @GET("/api/blog")
    suspend fun getBlog(): ServiceResponse<Blog>

    @GET("/api/blog/{id}")
    suspend fun getBlogById(@Path("id") id: Long): ServiceResponse<Blog>
}