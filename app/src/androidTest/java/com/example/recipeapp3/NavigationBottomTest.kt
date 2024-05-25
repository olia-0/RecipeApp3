package com.example.recipeapp3

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import com.example.recipeapp3.data.UserViewModel
import com.example.recipeapp3.navigation.BottomBar
import com.example.recipeapp3.navigation.NavigationGraph
import com.example.recipeapp3.ui.theme.RecipeApp3Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.test.espresso.device.filter.RequiresDisplay
import androidx.test.espresso.device.sizeclass.HeightSizeClass
import androidx.test.espresso.device.sizeclass.WidthSizeClass
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.screens.LoginScreen
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationBottomTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    val windowSize = WindowSizeClass.Compact


    private lateinit var navController: TestNavHostController

    @Before
    fun setupRecipeAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            when (windowSize) {
                WindowSizeClass.Compact -> RecipeAppCompact(navController = navController)
                WindowSizeClass.Medium -> RecipeAppMedium(navController = navController)
                WindowSizeClass.Expanded -> RecipeAppExpanded(navController = navController)
                else -> {}
            }
        }
    }


    @Test
    fun recipeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName("login")
    }
    @Test
    fun recipeNavHost_verifyBackNavigationNotShownOnLoginScreen() {
        composeTestRule.onNodeWithTag("back_button").assertDoesNotExist()
    }


    @Test
    fun recipeNavHost_clickLoginScreen_navigatesToHomeScreen() {
        composeTestRule.onNodeWithTag("login_username").performTextInput("user1")
        composeTestRule.onNodeWithTag("login_password").performTextInput("user1")
        composeTestRule.onNodeWithText("Login").performClick()
        navController.assertCurrentRouteName(AppRoute.HomeScreen.route)
    }



    @Test
    fun recipeNavHost_clickHomeScreen_navigatesToSearchScreen() {
        recipeNavHost_clickLoginScreen_navigatesToHomeScreen()
        //val stateRestorationTester = StateRestorationTester(composeTestRule)
        //stateRestorationTester.setContent { RecipeAppCompact() }
        val searchScreenTag = when (windowSize) {
            WindowSizeClass.Compact -> "search_compact"
            WindowSizeClass.Medium -> "search_medium"
            WindowSizeClass.Expanded -> "search_expanded"
            else -> {}
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(searchScreenTag.toString()).performClick()
        //println("AAAA"+searchScreenTag.toString())
        navController.assertCurrentRouteName(AppRoute.SearchScreen.route)
    }

    @Test
    fun recipeNavHost_clickHomeScreen_navigatesToSavedScreen() {
        recipeNavHost_clickLoginScreen_navigatesToHomeScreen()
        val searchScreenTag = when (windowSize) {
            WindowSizeClass.Compact -> "saved_compact"
            WindowSizeClass.Medium -> "saved_medium"
            WindowSizeClass.Expanded -> "saved_expanded"
            else -> {}
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(searchScreenTag.toString()).performClick()
        navController.assertCurrentRouteName(AppRoute.SavedScreen.route)


    }
    @Test
    fun recipeNavHost_clickHomeScreen_navigatesToProfileScreen() {
        recipeNavHost_clickLoginScreen_navigatesToHomeScreen()
        val searchScreenTag = when (windowSize) {
            WindowSizeClass.Compact -> "profile_compact"
            WindowSizeClass.Medium -> "profile_medium"
            WindowSizeClass.Expanded -> "profile_expanded"
            else -> {}
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag(searchScreenTag.toString()).performClick()
        navController.assertCurrentRouteName(AppRoute.ProfileScreen.route)


    }
    /*@RequiresDisplay(WidthSizeClass.Companion.WidthSizeClassEnum.MEDIUM,HeightSizeClass.Companion.HeightSizeClassEnum.EXPANDED)
    @Test
    fun recipeNavHost_clickHomeScreen_navigatesToSearchScreen2() {
        recipeNavHost_clickLoginScreen_navigatesToHomeScreen()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("search_medium").performClick()
        navController.assertCurrentRouteName(AppRoute.SearchScreen.route)
    }*/
}



fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(@StringRes id: Int): SemanticsNodeInteraction =
    onNodeWithText(activity.getString(id))


