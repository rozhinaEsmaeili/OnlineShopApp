package com.example.onlineshopapp2.repositories.products

import com.example.onlineshopapp2.api.products.ProductApi
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.products.Product
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductRepository @Inject constructor(private val api: ProductApi) {

    suspend fun getProducts(pageIndex: Int, pageSize: Int): ServiceResponse<Product> {
        return try {
            api.getProducts(pageIndex, pageSize)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getProductById(id: Long): ServiceResponse<Product> {
        return try {
            api.getProductById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getProductByCategoryId(
        id: Long,
        pageIndex: Int,
        pageSize: Int
    ): ServiceResponse<Product> {
        return try {
            api.getProductByCategoryId(id, pageIndex, pageSize)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getNewProducts(): ServiceResponse<Product> {
        return try {
            api.getNewProducts()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getPopularProducts(): ServiceResponse<Product> {
        return try {
            api.getPopularProducts()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}