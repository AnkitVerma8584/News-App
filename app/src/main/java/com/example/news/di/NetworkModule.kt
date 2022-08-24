package com.example.news.di

import com.example.news.data.remote.Api
import com.example.news.data.remote.dao.NewsDao
import com.example.news.data.remote.repository.NewsRepositoryImpl
import com.example.news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @CafeBurpRetrofitBuild
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNewsApiInterface(@CafeBurpRetrofitBuild retrofit: Retrofit): NewsDao =
        retrofit.create(NewsDao::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(newsDao: NewsDao): NewsRepository = NewsRepositoryImpl(newsDao)

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CafeBurpRetrofitBuild
