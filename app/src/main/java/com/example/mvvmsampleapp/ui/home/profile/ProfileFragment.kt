package com.example.mvvmsampleapp.ui.home.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.databinding.ProfileFragmentBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.time.LocalDateTime

class ProfileFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private lateinit var _viewModel: ProfileViewModel
    private val factory:ProfileViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        _viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        Log.d("clock", LocalDateTime.now().toString())
        binding.viewmodel = _viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
