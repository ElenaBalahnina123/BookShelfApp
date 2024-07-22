package com.example.bookshelf.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query


interface BookShelfService {
    @GET("volumes")
    suspend fun getBookShelf(
        @Query("q") query : String,
        @Query("maxResults") maxResults : Int
    ) : Items
}

@Serializable
data class Items(
    @SerialName("items")
    val items : List<VolumeInfo>
)

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