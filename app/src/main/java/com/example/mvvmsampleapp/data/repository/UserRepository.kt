package com.example.mvvmsampleapp.data.repository

import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.db.entity.User
import com.example.mvvmsampleapp.data.network.MyApi
import com.example.mvvmsampleapp.data.network.SafeApiRequest
import com.example.mvvmsampleapp.data.network.response.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db:AppDatabase
) : SafeApiRequest(){

    suspend fun  userLogin(email: String, password: String) : AuthResponse{
        return  apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user:User) = db.getUserDao().upSert(user)

    fun  getUser() = db.getUserDao().getUser()
}