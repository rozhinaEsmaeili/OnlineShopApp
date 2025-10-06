package com.example.onlineshopapp2.repositories.base

open class BaseRepository{

    protected fun prepareToken(token: String): String {
        var fixedToken = token
        if (!fixedToken.lowercase().startsWith("bearer")) {
            fixedToken = "Bearer $token"
        }
        return fixedToken
    }
}