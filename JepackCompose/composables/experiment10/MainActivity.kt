package com.salmakhd.android.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salmakhd.android.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Column(
                    Modifier
                        .fillMaxSize()
                ) {

                    ComposableInfoColumn(
                        headers = listOf(
                            stringResource(id = R.string.first_box_header),
                            stringResource(id = R.string.second_box_header)
                        ),
                        bodies = listOf(
                            stringResource(id = R.string.first_box_body),
                            stringResource(id = R.string.second_box_body)
                        ),
                        backgroundColors = listOf(Color.Green, Color.Yellow)
                    )

                    ComposableInfoColumn(
                        headers = listOf(
                            stringResource(id = R.string.third_box_header),
                            stringResource(id = R.string.fourth_box_header)
                        ),
                        bodies = listOf(
                            stringResource(id = R.string.third_box_body),
                            stringResource(id = R.string.fourth_box_body)
                        ),
                        backgroundColors = listOf(Color.Red, Color.Blue)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ComposableInfoColumnPreview() {
    ComposableInfoColumn(
        headers = listOf(
            stringResource(id = R.string.first_box_header),
            stringResource(id = R.string.second_box_header)
        ),
        bodies = listOf(
            stringResource(id = R.string.first_box_body),
            stringResource(id = R.string.second_box_body)
        ),
        backgroundColors = listOf(Color.Green, Color.Yellow)
    )
}

@Composable
fun ComposableInfoColumn(
    headers: List<String>,
    bodies: List<String>,
    backgroundColors: List<Color>
) {
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ComposableInfo(header = headers[0], body = bodies[0], backgroundColor = backgroundColors[0])
        ComposableInfo(header = headers[1], body = bodies[1], backgroundColor = backgroundColors[1])
    }
}

@Composable
fun ComposableInfo(
    header: String,
    body: String,
    backgroundColor: Color
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(backgroundColor)
            .height(390.dp)
            .width(200.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text(
                text = header,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            Text(
                text = body,
                fontSize = 20.sp
            )
        }
    }
}