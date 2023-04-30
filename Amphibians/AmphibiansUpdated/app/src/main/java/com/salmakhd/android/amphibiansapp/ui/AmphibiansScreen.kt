package com.salmakhd.android.amphibiansapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.salmakhd.android.amphibiansapp.network.Amphibian
import com.salmakhd.android.amphibiansapp.R

@Composable
fun AmphibiansScreen (
    amphibianUiState: AmphibianUiState,
    modifier: Modifier = Modifier
) {
    // get the ui state and show the respective screen
    when(amphibianUiState) {
        is AmphibianUiState.Success -> AmphibiansList(amphibians= amphibianUiState.amphibians, modifier = modifier)
        is AmphibianUiState.Error -> ErrorScreen(modifier)
        is AmphibianUiState.Loading -> LoadingScreen(modifier)
    }
}

@Composable
fun AmphibiansList(
    amphibians:List<Amphibian>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
    ) {
        items(items = amphibians, key = {amphibian -> amphibian.name }) {amphibian ->
            AmphibianCard(
                name = amphibian.name,
                type = amphibian.type,
                description = amphibian.description,
                imgSrc = amphibian.imgSrc
            )
        }
    }
}

// define loading screen
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading_image_description)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "failed to load")
    }
}

@Composable
fun AmphibianCard(
    modifier: Modifier = Modifier,
    name: String,
    type: String,
    description: String,
    imgSrc: String
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // show title
            Text(
                text = "$name ($type)",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // show description
            Text(
                text = description,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )

            // define an Image type that works with the Coil library
            AsyncImage(
                // build an image request using the image URL
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imgSrc)
                    // add animation
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.amphibian_image_description),
                contentScale = ContentScale.FillBounds,
                // add placeholders for different response states
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
        }

    }
}