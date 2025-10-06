package com.example.onlineshopapp2.models.customers

import com.example.onlineshopapp2.db.models.UserEntity

data class UserVM(
    var address: String? = "",
    var customerId: Long? = null,
    var firstName: String? = "",
    var id: Long? = null,
    var lastName: String? = "",
    var oldPassword: String? = null,
    var password: String? = null,
    var phone: String? = "",
    var postalCode: String? = "",
    var repeatPassword: String? = null,
    var token: String? = null,
    var username: String? = null
) {
    fun convertToUserEntity(): UserEntity {
        return UserEntity(
            userId = id,
            firstName = firstName,
            lastName = lastName,
            address = address,
            customerId = customerId,
            phone = phone,
            postalCode = postalCode,
            token = token,
            username = username
        )
    }
}
