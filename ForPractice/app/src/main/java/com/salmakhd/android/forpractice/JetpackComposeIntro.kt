package com.salmakhd.android.forpractice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FiberDvr
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.WifiTetheringError
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JetpackComposeIntro() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "top bae")
            }
        },
        bottomBar = {
            BottomAppBar {
                Text(text="")
            }
        },
        floatingActionButton = {
            Icon(imageVector = Icons.Filled.WifiTetheringError, contentDescription = null)
        }
    ) { it ->

    }
    Surface(modifier = Modifier
        .padding(16.dp),
        color = Color.Green,
        border = BorderStroke(width=12.dp, color = Color.White),
        elevation = 28.dp
    ) {
        // will be incremented even without declaring it as state
        var count = 0

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        )  {
            Button(
                onClick = {count++ },
                elevation = ButtonDefaults.elevation(
                    pressedElevation = 10.dp,
                    defaultElevation = 20.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Red
                ),
                border = BorderStroke(color = Color.Black, width = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text="$count")
            }

            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text="Simple Button")
            }

            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.FiberDvr,
                    contentDescription = null,
                    tint = Color.Red
                )
            }

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Button")
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Filled.FiberDvr,
                    contentDescription = null,
                    tint = Color.Red
                )
            }


            val myFont = FontFamily(
                Font(R.font.poppins_extralight)
            )
            Text(
                modifier = Modifier
                    .width(500.dp)
                    .background(Color.Green),
                text = "salma",
                color = Color.Blue,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontFamily = myFont,
                letterSpacing = 12.sp,
                textDecoration = TextDecoration.LineThrough,
                textAlign = TextAlign.Justify,
                lineHeight = 60.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            var textValue by remember {
                mutableStateOf("")
            }

            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current
            TextField(
                value = textValue,
                onValueChange = {newText -> textValue = newText},
                label = {
                    Text(text="Name")
                },
                maxLines = 2,
                singleLine = true,
                modifier = Modifier.width(280.dp),
                placeholder = {
                    Text(text = "0912...")
                },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Email,
                        contentDescription = null,
                      //  tint = Color.Green
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Send, contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    // capitalization = KeyboardCapitalization.Characters
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onSend =
                    {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Blue,
                    focusedIndicatorColor = Color.Black,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,

                ),
                shape = RoundedCornerShape(20.dp)

            )

            OutlinedTextField(
                value = "",
                onValueChange = {}
            )

            BasicTextField(value = "", onValueChange = {})

            Card(

            ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(painterResource(id = com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_focused), contentDescription = null)

            }
            }

            Image(
                painter = painterResource(id = com.google.android.gms.base.R.drawable.common_full_open_on_phone),
                contentDescription = null,
                alignment = Alignment.CenterEnd,
                alpha = 0.2f,
                modifier = Modifier.size(200.dp)
            )
        }

    }
}

@Preview
@Composable
fun JetpackComposeIntroPreview() {
    JetpackComposeIntro()
}
