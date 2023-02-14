package com.salmakhd.composecourseyoutube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // create a recycler view
            LazyColumn {
                // represents the items within the recycler view
                items(5000) {
                    // enter code for a single item
                    Text(
                        text = "Item #$it",
                        fontSize = 24.sp,
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                        ,modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                }

                /*
               // alternative approach
               itemsIndexed(
                   listOf(1,2,3,4)
               ) {_, number ->
                   // enter code for a single item
                   Text(
                       text = "Item #$number",
                       fontSize = 24.sp,
                       fontWeight = FontWeight.Bold,
                       textAlign = TextAlign.Center
                       ,modifier = Modifier
                           .fillMaxWidth()
                           .padding(24.dp)
                   )
               }
                */

            }
        }
    }
}

