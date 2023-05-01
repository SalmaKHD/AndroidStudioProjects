package com.salmakhd.android.amphibiansapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salmakhd.android.amphibiansapp.ui.AmphibianViewModel
import com.salmakhd.android.amphibiansapp.ui.AmphibiansScreen

@Composable
fun AmphibianApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.appname)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            // create a viewModel using the factory method and pass
            // the ui state to the UI layer
            val amphibianViewModel: AmphibianViewModel = viewModel(factory = AmphibianViewModel.Factory)
            AmphibiansScreen(
                amphibianUiState = amphibianViewModel.amphibianUiState
            )
        }
    }
}