package com.example.news.presentation.ui.app.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {


    var isLoading = mutableStateOf(true)

    var newsQuery by mutableStateOf("Hello World")

    val a = "my Home"

    init {
        Log.e("TAG", "Home viewModel created")
        viewModelScope.launch {
            delay(2000)
            isLoading.value = false
        }
    }


}