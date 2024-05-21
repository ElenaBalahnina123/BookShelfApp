package com.example.waifuapp.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET


interface BookShelfService {
    @GET("volumes/s1gVAAAAYAAJ")
    suspend fun getBookShelf() : VolumeInfo
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