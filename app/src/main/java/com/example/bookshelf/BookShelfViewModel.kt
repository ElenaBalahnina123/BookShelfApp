package com.example.bookshelf

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.domain.Book
import com.example.bookshelf.domain.BookShelfRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookShelfViewModel @Inject constructor(
    private val repository: BookShelfRepository,
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow<List<Book>>(emptyList())

     val state = mutableStateFlow.asStateFlow()

    init {
      uiFlow()
    }

    private fun uiFlow(query: String = "сарамаас", maxResults: Int = 20) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                mutableStateFlow.value = emptyList()
                repository.getBookShelf(query, maxResults)
            }.onSuccess { bData ->
                mutableStateFlow.value = bData.map {
                    Book(
                        imgBook = it.imgBook,
                        title = it.title
                    )
                }
            }
        }
    }
}