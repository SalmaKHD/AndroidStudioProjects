package com.salmakhd.composecourseyoutube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.salmakhd.composecourseyoutube.ui.theme.ComposeCourseYouTubeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // define a FontFamily object
        val fontFamily = FontFamily(
            Font(R.font.gentium_book_plus_regular, FontWeight.Normal),
            Font(R.font.gentium_book_plus_bold, FontWeight.Bold),
        )

        setContent {
            ComposeCourseYouTubeTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color(0xFF101010)
                        )
                ) {
                    Text(
                        // apply different styles to separate segments of the text
                        text = buildAnnotatedString {

                            // this style will be applied to the letter "J"
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green,
                                    fontSize = 50.sp
                                )
                            ) {
                                append("J")
                            }

                            append("etpack ")

                            withStyle(
                                style = SpanStyle(
                                    color = Color.Green,
                                    fontSize = 50.sp
                                )
                            ) {
                                append("C")
                            }

                            append("ompose")
                        },
                        // default style
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}