package com.example.onlineshopapp2.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var address: String?,
    var customerId: Long?,
    var firstName: String?,
    var userId: Long?,
    var lastName: String?,
    var phone: String?,
    var postalCode: String?,
    var token: String?,
    var username: String?
)
