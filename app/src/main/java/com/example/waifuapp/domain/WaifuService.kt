package com.example.waifuapp.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture


interface WaifuService {
    @GET("volumes/s1gVAAAAYAAJ")
    suspend fun getWaifu() : VolumeInfo
}

@Serializable
data class VolumeInfo (
    @SerialName("volumeInfo")
    val volumeInfo : ImageLinks,
)


@Serializable
data class ImageLinks(
    @SerialName("imageLinks")
    val imageLinks : Thumbnail,
    @SerialName("title")
    val title : String
)

@Serializable
data class Thumbnail(
    @SerialName("thumbnail")
    val thumbnail : String
)