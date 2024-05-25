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
import androidx.compose.ui.test.printToString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.screens.LoginViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*class LoginScreenTest {

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

        // Виконати дії зі зміною конфігурації (наприклад, розмір екрану)
        composeTestRule.onNodeWithTag("login_username").performTextInput("user1")
        composeTestRule.onNodeWithTag("login_password").performTextInput("user1")
        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.waitForIdle()
        //navController.assertCurrentRouteName(AppRoute.HomeScreen.route)

        stateRestorationTester.emulateSavedInstanceStateRestore()
        //navController.popBackStack()
        // Перевірити, що поле вводу логіну було відновлене після зміни конфігурації
        /*stateRestorationTester.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            RecipeApp(navController = navController)
        }*/

        composeTestRule.waitForIdle()
        composeTestRule.waitForIdle()
        composeTestRule.waitForIdle()


        // Додаткова перевірка, що дані відображаються на екрані
        composeTestRule.onNodeWithTag("login_password").assertTextContains("user1")
        //composeTestRule.onNodeWithTag("login_username").assertTextContains("")
        //composeTestRule.onNodeWithTag("login_password").assertTextContains("user1")


    }
}*/
class LoginScreenTest {

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
        composeTestRule.waitForIdle()

        stateRestorationTester.emulateSavedInstanceStateRestore()


        composeTestRule.onNodeWithTag("login_username").assertTextContains("user1")
        composeTestRule.onNodeWithTag("login_password").assertTextContains("user1")
    }
}


