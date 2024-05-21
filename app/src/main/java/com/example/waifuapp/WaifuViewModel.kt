package com.example.waifuapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waifuapp.domain.WaifuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class WaifuViewModel @Inject constructor(
    private val repository: WaifuRepository,
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow<BookStateVM>(BookStateVM.Initial)

    val state : Flow<BookStateVM> get() = mutableStateFlow.asStateFlow()

    init {
      uiFlow()
    }

     fun bookFlow() : Flow<WaifuScreenState> {
        return state.map { it.toScreen() }
    }


    private fun BookStateVM.toScreen(): WaifuScreenState {
        return when(this) {
            is BookStateVM.Error -> WaifuScreenState.Error(err = err)
            is  BookStateVM.Loading -> WaifuScreenState.Loading
            is BookStateVM.BookData -> WaifuScreenState.WaifuScreenData(
                imgWaifu = imgBook,
                title = title
            )
            is BookStateVM.Initial -> WaifuScreenState.Initial
        }
    }


    private fun uiFlow() {
        if(mutableStateFlow.value is BookStateVM.Loading)return
        viewModelScope.launch {
            mutableStateFlow.value = BookStateVM.Loading
            mutableStateFlow.value = try {
                BookStateVM.BookData(
                    imgBook = repository.getWaifu().volumeInfo.imageLinks.thumbnail,
                    title = repository.getWaifu().volumeInfo.title
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