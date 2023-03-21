package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

// test for different screen sizes
class ReplyAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // test compact screens
    @Test
    // use annotations to group tests for different screen sizes
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation() {
        composeTestRule.setContent{
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }

        // verify the existence of the bottom navigation in compact devices
        // Bottom navigation is displayed
        composeTestRule.onNodeWithTagForStringId(
            com.example.reply.R.string.navigation_bottom
        ).assertExists()
    }

    // test medium screens
    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail() {
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Medium)
        }

        // make sure NavigationRail is displayed
        composeTestRule.onNodeWithTagForStringId(
            com.example.reply.R.string.navigation_rail
        ).assertExists()
    }

    // test expanded screens
    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationDrawer() {
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }

        // make sure NavigationDrawer is displayed
        composeTestRule.onNodeWithTagForStringId(
            com.example.reply.R.string.navigation_drawer
        ).assertExists()
    }
}