package com.example.bookshelf

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.domain.Book
import com.example.bookshelf.domain.BookShelfRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookShelfViewModel @Inject constructor(
    private val repository: BookShelfRepository,
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow<List<Book>>(emptyList())

     val state = mutableStateFlow.asStateFlow()

    private val mutableSearchFlow = MutableStateFlow(TextFieldValue())
    val searchFlow get() = mutableSearchFlow.asStateFlow()

    fun onQueryChanged(query: TextFieldValue) {
        mutableSearchFlow.value = query
    }


    fun onSearchClick(maxResults : Int = 40) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.getBookShelf(mutableSearchFlow.value.text, maxResults)
            }.onSuccess { list ->
                mutableStateFlow.value = list
            }.onFailure {
                Log.e("ERR","error",it)
            }
        }
    }
}
