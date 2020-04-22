package com.example.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entity.User
import com.example.mvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvmsampleapp.util.hide
import com.example.mvvmsampleapp.util.show
import com.example.mvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSucces(user: User) {
        progress_bar.hide()
        toast("${user.name} is Logged on")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
        Log.d("fail", message)
    }
}
