package com.example.onlineshopapp2.api.site

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.site.Content
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {

    @GET("/api/content")
    suspend fun getContent(): ServiceResponse<Content>

    @GET("/api/content/{title}")
    suspend fun getContentByTitle(@Path("title") title: String): ServiceResponse<Content>
}