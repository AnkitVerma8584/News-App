package com.example.news.domain.repository

import com.example.news.domain.modals.News
import com.example.news.domain.util.Resource

interface NewsRepository {

    suspend fun getNews(): Resource<List<News>>

    suspend fun getNews(query: String): Resource<List<News>>
}
