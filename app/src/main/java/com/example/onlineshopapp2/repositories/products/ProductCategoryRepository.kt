package com.example.onlineshopapp2.repositories.products

import com.example.onlineshopapp2.api.products.ProductCategoryApi
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.ProductCategory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi) {

    suspend fun getCategories(): ServiceResponse<ProductCategory> {
        return try {
            api.getCategories()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getCategoryById(id: Long): ServiceResponse<ProductCategory> {
        return try {
            api.getCategoryById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}