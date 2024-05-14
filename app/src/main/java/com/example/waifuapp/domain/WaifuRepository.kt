package com.example.waifuapp.domain

import javax.inject.Inject

interface WaifuRepository {
      fun getWaifu() : Waifu
}

class WaifuRepositoryImpl @Inject constructor(
    private val waifuService: WaifuService
) : WaifuRepository {
    override fun getWaifu() : Waifu {
        return waifuService.getWaifu().get()

    }
}

