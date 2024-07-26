package com.example.bookshelf.domain

import javax.inject.Inject

interface BookShelfRepository {
     suspend fun getBookShelf(query : String,maxResults : Int) : List<Book>
}

class BookShelfRepositoryImpl @Inject constructor(
    private val bookService: BookShelfService
) : BookShelfRepository {
    override suspend fun getBookShelf(query : String,maxResults : Int) : List<Book> {
        return bookService.getBookShelf(query, maxResults).items.map {
            Book(
                imgBook = it.volumeInfo.imageLinks?.thumbnail,
                title = it.volumeInfo.title,
                previewLink = it.volumeInfo.previewLink,
                description = it.volumeInfo.description,
                author = it.volumeInfo.authors
            )
        }
    }
}

data class Book(
    val imgBook : String?,
    val title : String,
    val previewLink : String?,
    val description : String?,
    val author : List<String>?
)
