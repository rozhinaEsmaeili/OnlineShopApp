package com.example.onlineshopapp2.api.site

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.site.Slider
import retrofit2.http.GET
import retrofit2.http.Path

interface SliderApi {

    @GET("/api/slider")
    suspend fun getSlider(): ServiceResponse<Slider>

    @GET("/api/slider/{id}")
    suspend fun getSliderById(@Path("id") id: Long): ServiceResponse<Slider>
}