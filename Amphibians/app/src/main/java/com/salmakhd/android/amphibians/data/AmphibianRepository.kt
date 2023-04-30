package com.salmakhd.android.amphibians.data

import com.salmakhd.android.amphibians.network.Amphibian
import com.salmakhd.android.amphibians.network.AmphibianApiService

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