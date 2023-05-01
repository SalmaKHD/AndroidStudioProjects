package com.salmakhd.android.amphibiansapp

import com.salmakhd.android.amphibiansapp.network.Amphibian
import com.salmakhd.android.amphibiansapp.network.AmphibianApiService

object FakeAmphibianDataSource {
    val amphibiansList = listOf(
        Amphibian(
            name = "aaa",
            type= "hoo",
            description = "something something",
            imgSrc = "img1"
        ),
        Amphibian(
            name = "bbb",
            type= "hoo",
            description = "something something",
            imgSrc = "img2"
        )
    )
}

class FakeAmphibiansApiService: AmphibianApiService {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeAmphibianDataSource.amphibiansList
    }
}