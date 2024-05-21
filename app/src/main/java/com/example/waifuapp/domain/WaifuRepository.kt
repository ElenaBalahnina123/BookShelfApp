package com.example.waifuapp.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface WaifuRepository {
     suspend fun getWaifu() : VolumeInfo
}

class WaifuRepositoryImpl @Inject constructor(
    private val waifuService: WaifuService
) : WaifuRepository {
    override suspend fun getWaifu() : VolumeInfo {
        return waifuService.getWaifu()

    }
}

