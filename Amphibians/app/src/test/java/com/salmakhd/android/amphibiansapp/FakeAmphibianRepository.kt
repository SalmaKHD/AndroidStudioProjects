package com.salmakhd.android.amphibiansapp

import com.salmakhd.android.amphibiansapp.data.AmphibianRepository
import com.salmakhd.android.amphibiansapp.network.Amphibian

class FakeAmphibianRepository: AmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeAmphibianDataSource.amphibiansList
    }
}