package com.salmakhd.android.superheroes.superheroes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salmakhd.android.superheroes.R
import com.salmakhd.android.superheroes.model.Hero
import com.salmakhd.android.superheroes.model.HeroesRepository
import com.salmakhd.android.superheroes.ui.theme.SuperheroesTheme

@Composable
@Preview
private fun SuperheroListItemPreview() {
    SuperheroesTheme() {
        SuperheroListItem(
            heroNameRes = R.string.hero1,
            heroDescriptionRes = R.string.description1,
            heroImageRes = R.drawable.android_superhero1
        )
    }
}

@Composable
fun SuperheroListItem(
    @StringRes heroNameRes: Int,
    @StringRes heroDescriptionRes: Int,
    @DrawableRes heroImageRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(85.dp)
            .fillMaxWidth(),
        elevation = 3.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(.75f)
            ) {
                Text(
                    text = stringResource(id = heroNameRes),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = stringResource(id = heroDescriptionRes),
                    style = MaterialTheme.typography.body1
                )
            }

            Image(
                painter = painterResource(id = heroImageRes),
                contentDescription = stringResource(id = heroDescriptionRes),
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(MaterialTheme.shapes.small),
            )
        }
    }
}

@Composable
@Preview
private fun superheroesListPreview() {
    SuperheroesList(heroesList = HeroesRepository.heroes)
}

@Composable
fun SuperheroesList(
    heroesList: List<Hero>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(heroesList) { _, superhero ->
            SuperheroesTheme() {
                SuperheroListItem(
                    heroNameRes = superhero.nameRes,
                    heroDescriptionRes = superhero.descriptionRes ,
                    heroImageRes = superhero.imageRes)
            }

        }
    }

}