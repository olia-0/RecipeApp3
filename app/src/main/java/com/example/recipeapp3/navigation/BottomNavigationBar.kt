package com.example.recipeapp3.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.data.R
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipeapp3.data.RecipeByCategoryViewModel
import com.example.recipeapp3.data.RecipeCategoryViewModel
import com.example.recipeapp3.data.RecipeDetailsViewModel
import com.example.recipeapp3.data.RecipeFullViewModel
import com.example.recipeapp3.data.SavedRecipeViewModel
//import com.example.recipeapp3.data.User
import com.example.recipeapp3.data.UserViewModel
import com.example.recipeapp3.datastore.DataStoreManager
import com.example.recipeapp3.screens.CategoryScreen
//import com.example.recipeapp3.HomeScreen
//import com.example.recipeapp3.LoginScreen
//import com.example.recipeapp3.ProfileScreen
//import com.example.recipeapp3.SavedScreen
//import com.example.recipeapp3.SearchScreen
//import com.example.recipeapp3.data.currentUser
import com.example.recipeapp3.screens.HomeScreen
import com.example.recipeapp3.screens.LoginScreen
import com.example.recipeapp3.screens.LoginViewModel
import com.example.recipeapp3.screens.ProfileScreen
import com.example.recipeapp3.screens.RecipeScreen
import com.example.recipeapp3.screens.RegistrationScreen
import com.example.recipeapp3.screens.SavedScreen
import com.example.recipeapp3.screens.SearchScreen
import com.example.recipeapp3.screens.SearchViewModel
import com.example.recipeapp3.screens.StartScreen
import com.example.recipeapp3.ui.theme.MyYellow


@Composable
fun NavigationGraph(
    navController: NavHostController,
    dataStoreManager: DataStoreManager,
    textSize: MutableState<Int>,
    bgColor: MutableState<ULong>,
    recipeFullViewModel: RecipeFullViewModel,
    recipeCategoryViewModel: RecipeCategoryViewModel,
    recipeByCategoryViewModel: RecipeByCategoryViewModel,
    recipeDetailsViewModel: RecipeDetailsViewModel,
    recipeSavedViewModel: SavedRecipeViewModel,
    userViewModel: UserViewModel,
    loginViewModel: LoginViewModel = viewModel(),
    searchViewModel: SearchViewModel = viewModel()
) {
    NavHost(navController, startDestination = AppRoute.StartScreen.route) {
        composable(AppRoute.HomeScreen.route) {
            HomeScreen(navController,recipeCategoryViewModel.recipeCategoryUiState,textSize, bgColor, recipeCategoryViewModel)
        }
        composable(AppRoute.SearchScreen.route) {
            SearchScreen(navController, recipeFullViewModel.recipeFullUiState,textSize,bgColor, searchViewModel, recipeFullViewModel,recipeDetailsViewModel)

        }
        composable(AppRoute.SavedScreen.route) {
            SavedScreen(navController,recipeSavedViewModel.recipeSavedUiState,recipeDetailsViewModel)
        }

        composable(AppRoute.ProfileScreen.route ) {
            ProfileScreen(dataStoreManager,textSize,bgColor,userViewModel)
            //ProfileScreen()
        }
        composable(AppRoute.LoginScreen.route) {
            LoginScreen(navController, userViewModel,loginViewModel )
        }
        composable(AppRoute.RegistrationScreen.route) {
            RegistrationScreen(navController, userViewModel )
        }
        composable(AppRoute.CategoryScreen.route) {
            CategoryScreen(navController,recipeByCategoryViewModel.recipeByCategoryUiState,recipeByCategoryViewModel,recipeCategoryViewModel,recipeDetailsViewModel)
        }

        composable(AppRoute.RecipeScreen.route) {
            RecipeScreen(navController,recipeDetailsViewModel.recipeDetailsUiState,recipeDetailsViewModel,recipeByCategoryViewModel,recipeSavedViewModel)
        }
        composable(AppRoute.StartScreen.route) {
            StartScreen(navController)
        }
    }
}
@Composable
fun BottomBar(
    navController: NavHostController,
    state: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val screens = listOf(
        AppRoute.HomeScreen,
        AppRoute.SearchScreen,
        AppRoute.SavedScreen,
        AppRoute.ProfileScreen
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->

            NavigationBarItem(
                modifier = Modifier.testTag(screen.route+"_compact"),

                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(painter = painterResource(id = screen.iconId), contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.LightGray,
                    selectedTextColor = MyYellow,
                    selectedIconColor = MyYellow,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = Color.White
                ),
            )
        }
    }
}
@Composable
fun MediumNavigationRail(navController: NavHostController) {
    val screens = listOf(
        AppRoute.HomeScreen,
        AppRoute.SearchScreen,
        AppRoute.SavedScreen,
        AppRoute.ProfileScreen
    )

    NavigationRail(
        modifier = Modifier.fillMaxHeight(),
        contentColor = Color.Black,
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        screens.forEach { screen ->
            NavigationRailItem(
                modifier = Modifier.testTag(screen.route+"_medium"),
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(painter = painterResource(id = screen.iconId), contentDescription = "")
                },

                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                colors = NavigationRailItemDefaults.colors(
                    unselectedTextColor = Color.LightGray,
                    selectedTextColor = MyYellow,
                    selectedIconColor = MyYellow,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = Color.White

                )


            )
        }
    }
}
@Composable
fun ExpandedPermanentNavigationDrawer(
    navController: NavHostController,
    dataStoreManager: DataStoreManager,
    textSize: MutableState<Int>,
    bgColor: MutableState<ULong>,
    recipeFullViewModel: RecipeFullViewModel,
    recipeCategoryViewModel: RecipeCategoryViewModel,
    recipeByCategoryViewModel: RecipeByCategoryViewModel,
    recipeDetailsViewModel: RecipeDetailsViewModel,
    recipeSavedViewModel: SavedRecipeViewModel,
    userViewModel: UserViewModel
) {
    val screens = listOf(
        AppRoute.HomeScreen,
        AppRoute.SearchScreen,
        AppRoute.SavedScreen,
        AppRoute.ProfileScreen
    )

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                screens.forEach { screen ->
                    NavigationDrawerItem(
                        modifier = Modifier.testTag(screen.route+"_expanded"),
                        label = { Text(text = screen.title) },
                        icon = {
                            Icon(painter = painterResource(id = screen.iconId), contentDescription = "")
                        },
                        selected = false, // Update this based on the selected screen
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedTextColor = Color.LightGray,
                            selectedTextColor = MyYellow,
                            selectedIconColor = MyYellow,
                            unselectedIconColor = Color.LightGray,

                        )
                    )
                }
            }


        },


        drawerState = rememberDrawerState(DrawerValue.Closed),
        content = {
            NavigationGraph(navController,dataStoreManager,textSize, bgColor, recipeFullViewModel,recipeCategoryViewModel,recipeByCategoryViewModel,recipeDetailsViewModel,recipeSavedViewModel, userViewModel)
        },

    )
}


