package com.example.news.data.remote.repository

import com.example.news.data.remote.dao.NewsDao
import com.example.news.domain.modals.News
import com.example.news.domain.repository.NewsRepository
import com.example.news.domain.util.Resource
import com.example.news.domain.util.StringUtil
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun getNews(): Resource<List<News>> {
        return try {
            val response = newsDao.getNews()
            if (response.status == "ok") {
                Resource.Success(response.articles)
            } else {
                Resource.Error(
                    StringUtil.DynamicText(response.message ?: "Some unknown error occurred")
                )
            }
        } catch (e: Exception) {
            Resource.Error(
                StringUtil.DynamicText("Please check your connection")
            )
        }
    }

    override suspend fun getNews(query: String): Resource<List<News>> {
        try {
            val response = newsDao.getNews(query)
            return if (response.status == "ok") {
                if (response.articles != null && response.articles.isEmpty())
                    Resource.Error(
                        StringUtil.DynamicText(
                            "No news available for above query. Try searching for something else"
                        )
                    )
                else Resource.Success(response.articles)

            } else {
                Resource.Error(
                    StringUtil.DynamicText(response.message ?: "Some unknown error occurred")
                )
            }
        } catch (e: Exception) {
            return Resource.Error(
                StringUtil.DynamicText(e.message ?: "Check your connection")
            )
        }
    }
}