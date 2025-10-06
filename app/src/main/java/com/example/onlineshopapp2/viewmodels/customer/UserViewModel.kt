package com.example.onlineshopapp2.viewmodels.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp2.models.ServiceResponse
import com.example.onlineshopapp2.models.customers.User
import com.example.onlineshopapp2.models.customers.UserVM
import com.example.onlineshopapp2.repositories.customer.UserRepository
import com.example.onlineshopapp2.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    var token: String = ThisApp.token

    fun getUserInfo(onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getUserInfo(token)
            onResponse(response)
        }
    }

    fun changePassword(user: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = repository.changePassword(user, token)
            onResponse(response)
        }
    }


    fun login(user: UserVM, onResponse: (response: ServiceResponse<UserVM>) -> Unit) {
        viewModelScope.launch {
            var response = repository.login(user)
            onResponse(response)
        }
    }

    fun register(user: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = repository.register(user)
            onResponse(response)
        }
    }

    fun update(user: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = repository.update(user, token)
            onResponse(response)
        }
    }
}