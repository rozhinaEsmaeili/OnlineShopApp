package com.example.onlineshopapp2.db.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp2.db.models.UserEntity
import com.example.onlineshopapp2.db.repository.UserEntityRepository

class UserEntityViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = UserEntityRepository(application)
    var cuurentUser = mutableStateOf<UserEntity?>(null)

    suspend fun insert(user: UserEntity) {
        repository.insert(user)
    }

    suspend fun update(user: UserEntity) {
        if (user.id <= 0) return
        repository.update(user)
    }

    suspend fun delete(user: UserEntity) {
        if (user.id <= 0) return
        repository.delete(user)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return repository.getCurrentUser()
    }

    fun isLoggedIn(): Boolean {
        return cuurentUser.value != null
    }
}