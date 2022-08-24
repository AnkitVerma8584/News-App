package com.example.news.presentation.ui.app.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.news.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {

    val a = "my Setting"

    init {
        Log.e("TAG", "Setting viewModel created")
    }
}