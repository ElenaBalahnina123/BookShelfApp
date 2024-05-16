package com.example.waifuapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waifuapp.domain.WaifuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Thread.State
import javax.inject.Inject

class WaifuViewModel @Inject constructor(
    private val repository: WaifuRepository,
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow<WaifuScreenState>(WaifuScreenState.Initial)

    val state : StateFlow<WaifuScreenState> get() = mutableStateFlow.asStateFlow()

    init {
        uiFlow()
    }

    private fun uiFlow() {
        if(mutableStateFlow.value is WaifuScreenState.Loading)return
        viewModelScope.launch {
            mutableStateFlow.value = WaifuScreenState.Loading
            mutableStateFlow.value = try {
                WaifuScreenState.WaifuScreenData(
                    imgWaifu = repository.getWaifu().url
                )
            } catch (err : Throwable) {
                WaifuScreenState.Error(err)
            }
        }
    }
}


sealed class WaifuStateVM {
    data object Initial : WaifuStateVM()
    data object Loading : WaifuStateVM()

    data class WaifuData(
        val imgWaifu: String
    ) : WaifuStateVM()

    data class Error(
        val err: Throwable
    ) : WaifuStateVM()
}