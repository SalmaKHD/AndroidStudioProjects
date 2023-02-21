package com.salmakhd.android.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salmakhd.android.jetpackcompose.ui.theme.JetpackComposeTheme
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface (
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

// add annotation for making the method public for testing
@VisibleForTesting
// part of app's logic
internal fun calculateTip (
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
): String {
    var tip = tipPercent / 100 * amount
    if (roundUp)
        tip = ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeScreen()
}

@Composable
fun TipTimeScreen() {
    /*
   This creates a State object which observes the value it contains.
   As soon as this value changes, recomposition occurs
    */
    /*
    remember: used to store the an object across recompositions
     */
    // define the state of the first field
    var amountInput by remember {
        mutableStateOf("")
    }
    // define the state of the tip filed
    var tipInput  by remember {
        mutableStateOf("")
    }

    // define the state of the switch
    var roundUp by remember {
        mutableStateOf(false)
    }

    val tipPercentage = tipInput.toDoubleOrNull() ?: 0.0
    // app logic
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercentage, roundUp)

    // LocalFocusManager -> used to control focus in Jetpack Compose
    val focusManager = LocalFocusManager.current

    Column (
        modifier = Modifier
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .height(32.dp))

        EditNumberField(
            label = R.string.bill_amount,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            value = amountInput,
            onValueChange = {amountInput = it}
        )

        EditNumberField(
            label = R.string.how_was_the_service,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions (
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            value = tipInput,
            onValueChange = { tipInput = it}
        )

        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = {
                roundUp = it
            }
        )

        Spacer(modifier = Modifier.padding(24.dp))

        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value:String,
    onValueChange: (String)->Unit,
    modifier: Modifier = Modifier
) {

    /*
    Explanation: amountInput defines the state of this composable, and any changes
    to it will cause the composable to be recomposed
     */
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        // set the keyboard type
        // pass a keyboard with custom arguments
        // default options like capitalization and auto-correction will be used
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        value = value,
        onValueChange = onValueChange,
        label = {
            // set the label to a text
            Text(
                text = stringResource(label)
            )
        }
    )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip))

        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),

            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray
            )
        )

    }
}