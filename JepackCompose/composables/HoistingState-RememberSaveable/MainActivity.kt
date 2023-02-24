package com.salmakhd.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salmakhd.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TextFieldScreen()
            }
        }
    }
}

@Composable
fun TextFieldScreen() {
    /*
    rememberSaveable will preserve the state across process
    destruction and recreation as well
     */
    var input by rememberSaveable() {
        mutableStateOf("Type something!")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .padding(200.dp)
        )
        RegistryField(
            input,
            // 'it' can be any object that is of type String! -> it refers
            // to the single parameter of the lambda function.
            { input = it },
            modifier = Modifier
                .align(
                    CenterHorizontally
                )
        )

    }
}

@Composable
fun RegistryField(
    textFieldValue: String,
    onTextFieldValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textFieldValue,
        onValueChange = onTextFieldValueChange,
        modifier = modifier
    )
}