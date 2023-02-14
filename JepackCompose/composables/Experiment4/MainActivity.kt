package com.salmakhd.composecourseyoutube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // returns a default state for a Scaffold
            val scaffoldState = rememberScaffoldState()

            // define a state for the text field
            var textFieldState by remember {
                mutableStateOf("")
            }

            // get a coroutine scope
            val scope = rememberCoroutineScope()

            // will help us arrange composables on our screen
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                scaffoldState = scaffoldState
            ) {padding ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                ) {
                    TextField(
                        // the value that will appear in the field
                        value = textFieldState,
                        // field header
                        label =  {
                            Text("Your name")
                        },
                        // defines what happens when the characters entered into the
                        // field change
                        onValueChange = {
                            textFieldState = it
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                        }
                    }) {
                        Text("Please Greet Me!")
                    }
                }
            }
        }
    }
}

