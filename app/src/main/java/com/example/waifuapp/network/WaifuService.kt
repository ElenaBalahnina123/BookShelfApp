package com.example.waifuapp.network

import retrofit2.Response
import retrofit2.http.GET
import java.util.concurrent.CompletableFuture


interface WaifuService {
    @GET("sfw/neko")
    fun getWaifu() : CompletableFuture<Waifu>
}

data class Waifu(
    val url : String
)