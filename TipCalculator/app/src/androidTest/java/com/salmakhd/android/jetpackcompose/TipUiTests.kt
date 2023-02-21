package com.salmakhd.android.jetpackcompose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.salmakhd.android.jetpackcompose.ui.theme.JetpackComposeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUiTests {
    // give you access to the MainActivity instance and the UI
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            JetpackComposeTheme {
                TipTimeScreen()
            }
        }
        // find the composable that displays "Bill Amount" and enter 10 as input
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")

        composeTestRule.onNodeWithText("Tip (%)")
            .performTextInput("20")

        // make sure that a node with the expected output exists on screen
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )

    }
}