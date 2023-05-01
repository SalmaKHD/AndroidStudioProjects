package com.salmakhd.android.amphibiansapp

import com.salmakhd.android.amphibiansapp.ui.AmphibianUiState
import com.salmakhd.android.amphibiansapp.ui.AmphibianViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AmphibianViewModelTest {
    @Test
    fun amphibianViewModel_getAmphibians_verifyAmphibianUiStateSuccess() = runTest {
        val amphibiansViewModel = AmphibianViewModel(
            amphibianRepository = FakeAmphibianRepository()
        )
        assertEquals(
            AmphibianUiState.Success(FakeAmphibianDataSource.amphibiansList),
            amphibiansViewModel.amphibianUiState
        )
    }
}