package com.example.news.data.remote

object Api {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "2555409d03f842ce97b30b47e530400e"
    const val GET_NEWS = "top-headlines?country=in"

    fun getNewsFromCountry(country: String = "in") =
        "https://newsapi.org/v2/top-headlines?country=$country"

    const val GET_NEWS_FROM_QUERY = "https://newsapi.org/v2/everything/"
}