package com.example.mvvmsampleapp.data.repository

import com.example.mvvmsampleapp.data.network.MyApi
import com.example.mvvmsampleapp.data.network.response.AuthResponse
import retrofit2.Response

class UserRepository {

    suspend fun  userLogin(email: String, password: String) : Response<AuthResponse>{
        return MyApi().userLogin(email, password)
    }
}