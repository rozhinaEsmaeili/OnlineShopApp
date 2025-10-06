package com.example.onlineshopapp2.repositories.customer

import com.example.onlineshopapp2.api.customers.UserApi
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.customers.User
import com.example.onlineshopapp2.models.customers.UserVM
import com.example.onlineshopapp2.repositories.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val api: UserApi) : BaseRepository() {

    suspend fun getUserInfo(token: String): ServiceResponse<User> {
        return try {
            api.getUserInfo(prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun changePassword(user: UserVM, token: String): ServiceResponse<User> {
        return try {
            api.changePassword(user, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun login(user: UserVM): ServiceResponse<UserVM> {
        return try {
            api.login(user)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun register(user: UserVM): ServiceResponse<User> {
        return try {
            api.register(user)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun update(user: UserVM, token: String): ServiceResponse<User> {
        return try {
            api.update(user, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}