package com.example.bookshelf.domain

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.googleapis.com/books/v1/")
//            .client(
//                OkHttpClient.Builder()
//                    .addNetworkInterceptor(HttpLoggingInterceptor().apply {
//                        level = HttpLoggingInterceptor.Level.BODY
//                    })
//                    .build()
//            )
            .build()
    }

    @Provides
    fun provideRetrofitService(retrofit: Retrofit) : BookShelfService {
        return retrofit.create(BookShelfService::class.java)
    }
}

