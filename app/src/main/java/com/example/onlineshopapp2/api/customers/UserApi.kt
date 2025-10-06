package com.example.onlineshopapp2.api.customers

import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.customers.User
import com.example.onlineshopapp2.models.customers.UserVM
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {

    @GET("/api/user")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): ServiceResponse<User>

    @PUT("/api/user/changePassword")
    suspend fun changePassword(
        @Body user: UserVM,
        @Header("Authorization") token: String
    ): ServiceResponse<User>

    @POST("/api/user/login")
    suspend fun login(
        @Body user: UserVM
    ): ServiceResponse<UserVM>

    @POST("/api/user/register")
    suspend fun register(
        @Body user: UserVM
    ): ServiceResponse<User>

    @PUT("/api/user/update")
    suspend fun update(
        @Body user: UserVM,
        @Header("Authorization") token: String
    ): ServiceResponse<User>
}