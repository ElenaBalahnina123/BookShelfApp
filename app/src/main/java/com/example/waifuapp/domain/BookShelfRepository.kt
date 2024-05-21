package com.example.waifuapp.domain

import javax.inject.Inject

interface BookShelfRepository {
     suspend fun getBookShelf() : VolumeInfo
}

class BookShelfRepositoryImpl @Inject constructor(
    private val bookService: BookShelfService
) : BookShelfRepository {
    override suspend fun getBookShelf() : VolumeInfo {
        return bookService.getBookShelf()

    }
}

