package com.example.onlineshopapp2.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp2.db.OnlineShopDataBase
import com.example.onlineshopapp2.db.dao.UserEntityDao
import com.example.onlineshopapp2.db.models.UserEntity

class UserEntityRepository(application: Application) {

    private var userDao: UserEntityDao
    private var currentUser: LiveData<UserEntity>

    init {
        var db = OnlineShopDataBase.getInstance(application)
        userDao = db.userDao()
        currentUser = userDao.get()
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return currentUser
    }

    suspend fun insert(user: UserEntity) {
        deleteAll()
        userDao.add(user)
    }

    suspend fun update(user: UserEntity) {
        userDao.update(user)
    }

    suspend fun delete(user: UserEntity) {
        userDao.delete(user)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }
}