package com.salmakhd.android.amphibiansapp.data

import com.salmakhd.android.amphibiansapp.network.Amphibian
import com.salmakhd.android.amphibiansapp.network.AmphibianApiService

interface AmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class DefaultAmphibianRepository(
private val amphibianApiService: AmphibianApiService
): AmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibianApiService.getAmphibians()
    }
}