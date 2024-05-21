package com.example.waifuapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waifuapp.domain.BookShelfRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookShelfViewModel @Inject constructor(
    private val repository: BookShelfRepository,
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow<BookStateVM>(BookStateVM.Initial)

    val state : Flow<BookStateVM> get() = mutableStateFlow.asStateFlow()

    init {
      uiFlow()
    }

     fun bookFlow() : Flow<BookShelfScreenState> {
        return state.map { it.toScreen() }
    }


    private fun BookStateVM.toScreen(): BookShelfScreenState {
        return when(this) {
            is BookStateVM.Error -> BookShelfScreenState.Error(err = err)
            is  BookStateVM.Loading -> BookShelfScreenState.Loading
            is BookStateVM.BookData -> BookShelfScreenState.WaifuScreenData(
                imgWaifu = imgBook,
                title = title
            )
            is BookStateVM.Initial -> BookShelfScreenState.Initial
        }
    }


    private fun uiFlow() {
        if(mutableStateFlow.value is BookStateVM.Loading)return
        viewModelScope.launch {
            mutableStateFlow.value = BookStateVM.Loading
            mutableStateFlow.value = try {
                BookStateVM.BookData(
                    imgBook = repository.getBookShelf().volumeInfo.imageLinks.thumbnail,
                    title = repository.getBookShelf().volumeInfo.title
                )
            } catch (err : Throwable) {
                BookStateVM.Error(err)
            }
        }
    }
}


sealed class BookStateVM {
    data object Initial : BookStateVM()
    data object Loading : BookStateVM()

    data class BookData(
        val imgBook: String,
        val title : String
    ) : BookStateVM()

    data class Error(
        val err: Throwable
    ) : BookStateVM()
}