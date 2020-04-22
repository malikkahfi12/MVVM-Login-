package com.example.mvvmsampleapp.data.network.response

import com.example.mvvmsampleapp.data.db.entity.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user:User?
)