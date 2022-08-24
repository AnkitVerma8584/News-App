package com.example.news.domain.util

data class RetrofitResult<T>(
    val status: String,
    val message: String? = null,
    val articles: T? = null,
    val code: String? = null,
)