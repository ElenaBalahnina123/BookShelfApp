package com.example.waifuapp.domain

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.waifu.pics/")
            .build()
    }

    @Provides
    fun provideRetrofitService(retrofit: Retrofit) : WaifuService {
        return retrofit.create(WaifuService::class.java)
    }
}

