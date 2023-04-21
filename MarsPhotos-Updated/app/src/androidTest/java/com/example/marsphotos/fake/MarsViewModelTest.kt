package com.example.marsphotos.fake

import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MarsViewModelTest {
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            // provide a fake repo to the ViewModel
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )

        assertEquals(
            // ensure the ViewModel acts as expected
            MarsUiState.Success(FakeDataSource.photosList),
            // uiState must be in Success state cause the repo will return data
            // for sure
            marsViewModel.marsUiState
        )
    }
}