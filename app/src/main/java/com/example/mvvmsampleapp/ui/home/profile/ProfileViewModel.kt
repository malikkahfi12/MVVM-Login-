package com.example.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()


}
