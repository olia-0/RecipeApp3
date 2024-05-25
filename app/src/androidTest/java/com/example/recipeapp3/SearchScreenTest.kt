package com.example.recipeapp3

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.recipeapp3.navigation.AppRoute
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController




    @Test
    fun loginScreen_configurationChange_usernameInputRestored() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)

        stateRestorationTester.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            RecipeApp(navController = navController)
        }

        composeTestRule.onNodeWithTag("login_username").performTextInput("user1")
        composeTestRule.onNodeWithTag("login_password").performTextInput("user1")
        composeTestRule.onNodeWithText("Login").performClick()
        navController.assertCurrentRouteName(AppRoute.HomeScreen.route)
        composeTestRule.onNodeWithText("Search").performClick()
        navController.assertCurrentRouteName(AppRoute.SearchScreen.route)
        composeTestRule.onNodeWithTag("search_textField").performTextInput("г")

        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.waitForIdle()
        composeTestRule.waitForIdle()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Login").performClick()
        navController.assertCurrentRouteName(AppRoute.HomeScreen.route)
        composeTestRule.onNodeWithText("Search").performClick()
        navController.assertCurrentRouteName(AppRoute.SearchScreen.route)
        composeTestRule.onNodeWithTag("search_textField").assertTextContains("г")


        composeTestRule.waitForIdle()
        composeTestRule.waitForIdle()
        composeTestRule.waitForIdle()

    }
}
