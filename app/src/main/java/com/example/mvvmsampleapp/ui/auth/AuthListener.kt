package com.example.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmsampleapp.data.db.entity.User

interface AuthListener {
    fun onStarted()
    fun onSucces(user:User)
    fun onFailure(message: String)
}