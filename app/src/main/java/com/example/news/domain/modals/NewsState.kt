package com.example.news.domain.modals

import com.example.news.domain.util.StringUtil

data class NewsState(
    val news: List<News>? = emptyList(),
    val isLoading: Boolean = false,
    val error: StringUtil? = null
)