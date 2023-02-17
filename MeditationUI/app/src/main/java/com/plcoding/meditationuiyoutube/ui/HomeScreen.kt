package com.plcoding.meditationuiyoutube.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.meditationuiyoutube.BottomMenuContent
import com.plcoding.meditationuiyoutube.Feature
import com.plcoding.meditationuiyoutube.R
import com.plcoding.meditationuiyoutube.standardQuadFromTo
import com.plcoding.meditationuiyoutube.ui.theme.*

@Composable
fun HomeScreen() {

    // The entire screen will be composed of a box with a Column inside it
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ) {
        Column {
            // define the top section of the UI
            GreetingSection()

            // define the chips in the UI
            ChipSelection(
                chips = listOf("Sweet sleep", "Insomnia", "Depression")
            )

            // define the card in the UI
            CurrentMeditation()

            // define the Featured section
            FeaturedSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Calming sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )

            // define the bottom navigation bar
            BottomMenu(
                items = listOf(
                    BottomMenuContent("Home", R.drawable.ic_home),
                    BottomMenuContent("Meditate", R.drawable.ic_bubble),
                    BottomMenuContent("Sleep", R.drawable.ic_moon),
                    BottomMenuContent("Music", R.drawable.ic_music),
                    BottomMenuContent("Profile", R.drawable.ic_profile),
                ),
               modifier = Modifier
                   .align(Alignment.BottomCenter)
            )
        }
    }
}

// define the bottom navigation bar
@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    // define a state for the selected item in the navigation bar
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row (
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed() { index, item ->
            BottomMenuItem(
                item = item,
                // check if the item has been clicked
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                // onItemClicked: () -> Unit
            }
        }

    }
}

// define one item in the bottom navigation bar
@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {
                onItemClicked()
            }
    ) {
        // define each icon in the item
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if(isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier
                    .size(20.dp)
            )
        }

        // define the text at the bottom of each icon
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )

    }
}

@Composable
// define the top section of the app
fun GreetingSection(
    name: String = "Salma"
) {
    Row(
        // the child composables will be anchored to the left and right of the screen
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        // define the text section of the UI
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            // define the first line of text
            Text(
                text = "Good morning $name",
                style = MaterialTheme.typography.h2
            )
            // define the second line of text
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body1
            )
        }

        // define the icon that will appear on the right
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp))
    }
}

// define the row of chips in the UI
@Composable
fun ChipSelection(
    chips: List<String>
) {
    // define a state for the currently selected chip
    var selectedChip by remember {
        mutableStateOf(0)
    }

    LazyRow() {
        // define how an item in the RecyclerView should look like
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChip = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChip == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(
                    text = chips[it],
                    color = TextWhite)
            }
        }
    }
}

// define the Current Meditation section of the UI
@Composable
fun CurrentMeditation(
    color:Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        // define the text section of the card
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            // define the first line of text
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.h2
            )
            // define the second line of text
            Text(
                text = "Meditation * 3-10 min",
                style = MaterialTheme.typography.body1
            )
        }

        // define the play icon
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp))
        }

    }
}

// define the Features section of the UI
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturedSection(
    features: List<Feature>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(15.dp)
        )

        // define a grid for presenting the features
        LazyVerticalGrid(
            // number of cells in each row
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end= 7.5.dp, bottom = 100.dp),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            // define each item in the grid
            items(features.size) {
                // define how a single item in the grid looks like
                FeatureItem(
                    feature = features[it]
                )

            }
        }
    }

}

// define each feature in the Features section
@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            // this will make sure the card will always be in the shape of a square
            .aspectRatio(1.0f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        // retrieve the total height and width of the box (will vary from one device to another)
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // define the points where the card's background color should change
        // to form a wave
        // all values obtained through trial and error
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        // define a path using the defined points
        val mediumColoredPath = Path().apply {
            // define the first point in the path
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat(), height.toFloat())
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // draw the waves in the background of a feature 
        Canvas( modifier = Modifier
            .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )

            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }

        // define the card
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            // define the text in a card
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )

            // define the icon in a card
            Icon(
                painter = painterResource(feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart))
        }

        // define the button in a card
        Text(
            text = "Start",
            color = TextWhite,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    // handle the click
                }
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(10.dp))
                .background(ButtonBlue)
                .padding(vertical = 6.dp, horizontal = 15.dp)
        )
    }
}