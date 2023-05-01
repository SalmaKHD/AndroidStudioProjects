package com.salmakhd.android.amphibiansapp

import com.salmakhd.android.amphibiansapp.data.DefaultAmphibianRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AmphibianRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = DefaultAmphibianRepository(
            amphibianApiService = FakeAmphibiansApiService()
        )
        // since repository.getMarsPhotos() is a suspend function, we
        // have to provide a CoroutineScope
        assertEquals(FakeAmphibianDataSource.amphibiansList, repository.getAmphibians())
    }
}