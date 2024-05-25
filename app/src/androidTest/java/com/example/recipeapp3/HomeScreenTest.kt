package com.example.recipeapp3

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.screens.Greeting
import com.example.recipeapp3.screens.HomeScreen
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//@RunWith(AndroidJUnit4::class)
/*class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController


    @Before
    fun recipeNavHost_clickLoginScreen_navigatesToHomeScreen() {

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            RecipeApp(navController = navController)
        }

        composeTestRule.onNodeWithTag("login_username").performTextInput("user1")
        composeTestRule.onNodeWithTag("login_password").performTextInput("user1")
        composeTestRule.onNodeWithText("Login").performClick()
        navController.assertCurrentRouteName(AppRoute.HomeScreen.route)

    }
    @Test
    fun greeting_containsWelcomeText() {
       composeTestRule.onNodeWithText("Ласкаво просимо до нашого додатку рецептів!").assertExists()
    }
    @Test
    fun homeScreenGridsDisplayed() {
        composeTestRule.onAllNodesWithTag("recipe_grid").assertCountEquals(1)
    }
    @Test
    fun greeting_containsText() {
        composeTestRule.onAllNodesWithTag("recipe_text").assertCountEquals(1)
    }




}*/
