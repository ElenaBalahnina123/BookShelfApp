package com.example.waifuapp.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import java.util.concurrent.CompletableFuture


interface WaifuService {
    @GET("sfw/neko")
    fun getWaifu() : CompletableFuture<Waifu>
}

@Serializable
data class Waifu(
    @SerialName("url")
    val url : String
)