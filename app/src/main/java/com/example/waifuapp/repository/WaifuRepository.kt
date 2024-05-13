package com.example.waifuapp.repository

import android.util.Log
import com.example.waifuapp.network.Waifu
import com.example.waifuapp.network.WaifuService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface WaifuRepository {
      fun getWaifu() : Waifu
}

class WaifuRepositoryImpl @Inject constructor(
    private val waifuService: WaifuService
) : WaifuRepository{
    override fun getWaifu() : Waifu {
        return waifuService.getWaifu().get()

    }
}

