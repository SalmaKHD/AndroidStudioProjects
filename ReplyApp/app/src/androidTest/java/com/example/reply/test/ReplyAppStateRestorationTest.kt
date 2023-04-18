package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

// test for configuration changes
class ReplyAppStateRestorationTest {
    // create a test rule
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // verify an email is still selected in compact form after a configuration change
    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        // Setup compact window
        // StateRestorationTester is used for testing a configuration change
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        // Given third email is displayed
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body)
            .assertIsDisplayed()

        // navigate to detailed page
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].subject)
            .performClick()

        // now we are in the detailed page screen

        // Verify that it shows the detailed screen for the correct email
        composeTestRule.onNodeWithContentDescriptionForStringId(
            com.example.reply.R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body).assertExists()

        // simulate a configuration change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // Verify that it still shows the detailed screen for the same email
        composeTestRule.onNodeWithContentDescriptionForStringId(
            com.example.reply.R.string.navigation_back
        ).assertExists()
        // verify the detailed email is there
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body).assertExists()
    }

    // test configuration changes on expanded screens
    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
        // set up expanded window
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(WindowWidthSizeClass.Expanded)
        }
        // make sure that a third email is displayed on screen
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].body)
            .assertIsDisplayed()

        // click on the third email
        composeTestRule.onNodeWithText(LocalEmailsDataProvider.allEmails[2].subject)
            .performClick()

        // Verify that third email is displayed on the details screen
        composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(LocalEmailsDataProvider.allEmails[2].body)))

        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // verify that a third email is displayed after a configuration change
        composeTestRule.onNodeWithTagForStringId(com.example.reply.R.string.details_screen).onChildren()
            .assertAny(hasAnyDescendant(hasText(LocalEmailsDataProvider.allEmails[2].body)))
    }



}