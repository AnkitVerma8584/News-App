package com.example.news.presentation.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.modals.NewsState
import com.example.news.domain.repository.NewsRepository
import com.example.news.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    var newsState by mutableStateOf(NewsState())
        private set

    var newsQuery by mutableStateOf("")

    init {
        viewModelScope.launch {
            newsState = newsState.copy(isLoading = true)
            newsState = when (val response = newsRepository.getNews()) {
                is Resource.Success -> {
                    newsState.copy(
                        isLoading = false,
                        news = response.data
                    )
                }
                is Resource.Error -> {
                    newsState.copy(
                        isLoading = false,
                        error = response.message
                    )
                }
            }

        }
    }

    fun querySearch() {
        viewModelScope.launch {
            if (newsQuery.isNotBlank()) {
                newsState = newsState.copy(isLoading = true, news = null)
                newsState = when (val response = newsRepository.getNews(newsQuery)) {
                    is Resource.Success -> {
                        newsState.copy(
                            isLoading = false,
                            news = response.data
                        )
                    }
                    is Resource.Error -> {
                        newsState.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }
            }
        }
    }


}