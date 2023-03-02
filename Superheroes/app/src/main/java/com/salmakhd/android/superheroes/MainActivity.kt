package com.salmakhd.android.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.salmakhd.android.superheroes.model.HeroesRepository
import com.salmakhd.android.superheroes.superheroes.SuperheroesList
import com.salmakhd.android.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {TopAppBar()}
                ) { padding ->
                    SuperheroesList(
                        heroesList = HeroesRepository.heroes,
                        modifier = Modifier
                    )
                }

            }
        }
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}
