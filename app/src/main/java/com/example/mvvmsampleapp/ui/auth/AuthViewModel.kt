package com.example.mvvmsampleapp.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.example.mvvmsampleapp.data.repository.UserRepository
import com.example.mvvmsampleapp.util.ApiException
import com.example.mvvmsampleapp.util.Coroutines
import com.example.mvvmsampleapp.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Data tidak boleh kosong")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSucces(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }
}