package com.example.onlineshopapp2.repositories.products

import com.example.onlineshopapp2.api.products.ColorApi
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.ProductColor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ColorRepository @Inject constructor(private val api: ColorApi) {

    suspend fun getColors(): ServiceResponse<ProductColor> {
        return try {
            api.getcolors()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getColorById(id: Long): ServiceResponse<ProductColor> {
        return try {
            api.getcolorById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}