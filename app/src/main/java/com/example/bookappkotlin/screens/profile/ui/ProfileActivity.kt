package com.example.bookappkotlin.screens.profile.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookappkotlin.databinding.ActivityProfileBinding
import com.example.bookappkotlin.screens.profile.viewmodel.ProfileViewModel
import com.example.bookappkotlin.screens.profile.viewmodel.ViewModelProfile
import org.koin.android.ext.android.inject

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ViewModelProfile
    private val viewModelProfile by inject<ViewModelProfile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDataBinding()
    }

    override fun onStart() {
        super.onStart()
        viewModel = viewModelProfile
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    @SuppressLint("CheckResult")
    private fun userDataBinding() {
        viewModelProfile.dataProfileAccountLiveData.observe(this) { response ->
            binding.username.text = response.name
        }
    }
}