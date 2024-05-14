package com.example.waifuapp

import androidx.lifecycle.ViewModel
import com.example.waifuapp.domain.WaifuRepository
import javax.inject.Inject

class WaifuViewModel @Inject constructor(
    private val repository: WaifuRepository,
) : ViewModel() {

    fun getWaifu() : WaifuScreenState.WaifuScreenData {
       return WaifuScreenState.WaifuScreenData(
               imgWaifu = repository.getWaifu().url
           )
    }
}

data class WaifuData(
        val imgWaifu : String
)