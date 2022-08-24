package com.example.news.data.remote.dao

import com.example.news.data.remote.Api
import com.example.news.domain.modals.News
import com.example.news.domain.util.RetrofitResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsDao {

    @Headers("Authorization: ${Api.API_KEY}")
    @GET(Api.GET_NEWS)
    suspend fun getNews(): RetrofitResult<List<News>>

    @Headers("Authorization: ${Api.API_KEY}")
    @GET(Api.GET_NEWS_FROM_QUERY)
    suspend fun getNews(
        @Query("q") query: String
    ): RetrofitResult<List<News>>
}