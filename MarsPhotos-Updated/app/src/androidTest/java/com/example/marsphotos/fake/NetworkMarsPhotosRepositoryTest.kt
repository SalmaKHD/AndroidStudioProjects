package com.example.marsphotos.fake

import com.example.marsphotos.data.DefaultMarsPhotosRepository
import junit.framework.TestCase.assertEquals
import org.junit.Test
import kotlinx.coroutines.test.runTest

class NetworkMarsPhotosRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = DefaultMarsPhotosRepository(
            marsApiService = FakeMarsApiService()
        )
        // since repository.getMarsPhotos() is a suspend function, we
        // have to provide a CoroutineScope
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }
}