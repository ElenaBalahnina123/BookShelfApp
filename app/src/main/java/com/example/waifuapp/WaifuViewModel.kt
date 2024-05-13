package com.example.waifuapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide
import com.example.waifuapp.repository.WaifuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class WaifuViewModel @Inject constructor(
   private val repository: WaifuRepository,
) : ViewModel() {

    fun getWaifu() : WaifuScreenData {
       return WaifuScreenData(
               imgWaifu = repository.getWaifu().url
           )
    }
}

data class WaifuData(
        val imgWaifu : String
    )