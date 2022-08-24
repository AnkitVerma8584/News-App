package com.example.news.presentation.ui.app.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.news.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {

    val a = "my Profile"

    init {
        Log.e("TAG", "profile viewModel created")
    }
}