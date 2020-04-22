package com.example.mvvmsampleapp.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repository.UserRepository
import com.example.mvvmsampleapp.util.Coroutines

class AuthViewModel : ViewModel() {
    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun  onLoginButtonClick(view : View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Data tidak boleh kosong")
            return
        }

        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful){
                authListener?.onSucces(response.body()?.user!!)
            } else {
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }
    }
}