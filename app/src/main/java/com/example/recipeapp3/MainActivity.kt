package com.example.recipeapp3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.VerticalScrollbar

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.recipeapp3.data.Recipe
import com.example.recipeapp3.data.RecipeByCategoryViewModel
import com.example.recipeapp3.data.RecipeCategoryViewModel
import com.example.recipeapp3.data.RecipeDetailsViewModel
import com.example.recipeapp3.data.RecipeFull
import com.example.recipeapp3.data.RecipeFullViewModel
import com.example.recipeapp3.data.SavedRecipeViewModel
import com.example.recipeapp3.data.UserViewModel
import com.example.recipeapp3.datastore.DataStoreManager
import com.example.recipeapp3.db.SavedRecipeDatabase
import com.example.recipeapp3.db.SavedRecipesRepository
import com.example.recipeapp3.db.UsersDatabase
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.navigation.BottomBar
import com.example.recipeapp3.navigation.   BottomBar
import com.example.recipeapp3.navigation.ExpandedPermanentNavigationDrawer
import com.example.recipeapp3.navigation.MediumNavigationRail
import com.example.recipeapp3.navigation.NavigationGraph
import com.example.recipeapp3.ui.theme.MyTurquoise
import com.example.recipeapp3.ui.theme.RecipeApp3Theme
import com.example.recipeapp3.ui.theme.Red
import com.example.recipeapp3.ui.theme.White
import kotlinx.coroutines.flow.collect
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(this)
        Log.d("MainActivity", "AAA onCreate")
        setContent {
           RecipeApp3Theme {
                val db = UsersDatabase.getDatabase(this)
                val userDao = db.userDao()
                val userViewModel = UserViewModel(userDao)
                //val dataStoreManager = DataStoreManager(this)
                val bgColor = remember {
                    mutableStateOf(White.value)
                }
                val textSize = remember {
                    mutableStateOf(26)
                }

                LaunchedEffect(key1 = dataStoreManager.getSettings()) {
                    dataStoreManager.getSettings().collect { settings ->
                        bgColor.value = settings.bgColor.toULong()
                        textSize.value = settings.textSize
                        Log.d("MainActivity", "AAA Color  " + bgColor.value)
                        Log.d("MainActivity", "AAA Text  " + textSize.value)
                    }
                }

                Log.d("MainActivity", "AAA Color  "+bgColor.value)
                Log.d("MainActivity", "AAA Text  "+textSize.value)
                Surface (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    //color = Color(bgColor.value)
                    //color = Color(Red.value)

                ) {
                    RecipeApp(userViewModel,dataStoreManager,textSize,bgColor)

                }
                //RecipeApp(userViewModel,dataStoreManager,textSize)

           }
        }
    }

}

@Composable
fun RecipeApp(
    userViewModel: UserViewModel,
    dataStoreManager: DataStoreManager,
    textSize: MutableState<Int>,
    bgColor: MutableState<ULong>,
    navController: NavHostController = rememberNavController()
) {

    val windowSizeClass = calculateWindowSizeClass(LocalConfiguration.current.screenWidthDp.dp)

    val recipeFullViewModel: RecipeFullViewModel = viewModel(factory = RecipeFullViewModel.Factory)
    val recipeCategoryViewModel: RecipeCategoryViewModel = viewModel(factory = RecipeCategoryViewModel.Factory)
    val recipeByCategoryViewModel: RecipeByCategoryViewModel = viewModel(factory = RecipeByCategoryViewModel.Factory)
    val recipeDetailsViewModel: RecipeDetailsViewModel = viewModel(factory = RecipeDetailsViewModel.Factory)
    val recipeSavedViewModel: SavedRecipeViewModel = viewModel(factory = SavedRecipeViewModel.Factory)


    when (windowSizeClass) {
        WindowSizeClass.Compact -> RecipeAppCompact(navController,dataStoreManager,textSize,bgColor,recipeFullViewModel,recipeCategoryViewModel,recipeByCategoryViewModel,recipeDetailsViewModel,recipeSavedViewModel,userViewModel)
        WindowSizeClass.Medium -> RecipeAppMedium(navController,dataStoreManager, textSize,bgColor, recipeFullViewModel,recipeCategoryViewModel,recipeByCategoryViewModel,recipeDetailsViewModel,recipeSavedViewModel,userViewModel)
        WindowSizeClass.Expanded -> RecipeAppExpanded(navController,dataStoreManager, textSize,bgColor, recipeFullViewModel,recipeCategoryViewModel,recipeByCategoryViewModel,recipeDetailsViewModel,recipeSavedViewModel,userViewModel)
    }
}

@Composable
fun RecipeAppCompact(
    navController: NavHostController = rememberNavController(),
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
    val windowSizeClass = calculateWindowSizeClass(LocalConfiguration.current.screenWidthDp.dp)
    var buttonsVisible = remember { mutableStateOf(true) }

    when (windowSizeClass) {
        WindowSizeClass.Compact -> {
            Scaffold(
                bottomBar = {
                    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                    if (currentRoute != AppRoute.LoginScreen.route &&
                        currentRoute != AppRoute.RegistrationScreen.route &&
                        currentRoute != AppRoute.StartScreen.route &&
                        currentRoute != AppRoute.RecipeScreen.route
                        ) {
                        BottomBar(
                            navController = navController,
                            state = buttonsVisible,
                            modifier = Modifier
                        )
                    }
                }) { paddingValues ->
                Box(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    NavigationGraph(navController = navController,dataStoreManager,textSize, bgColor, recipeFullViewModel,recipeCategoryViewModel,recipeByCategoryViewModel, recipeDetailsViewModel,recipeSavedViewModel,userViewModel)
                }
            }
        }
        else -> { }
    }
}

@Composable
fun RecipeAppMedium(
    navController: NavHostController = rememberNavController(),
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

    Row(modifier = Modifier.fillMaxSize()) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        if (currentRoute != AppRoute.LoginScreen.route &&
            currentRoute != AppRoute.RegistrationScreen.route &&
            currentRoute != AppRoute.StartScreen.route &&
            currentRoute != AppRoute.RecipeScreen.route
            ) {
            MediumNavigationRail(navController = navController)
        }
        NavigationGraph(navController = navController,dataStoreManager,textSize, bgColor, recipeFullViewModel,recipeCategoryViewModel,recipeByCategoryViewModel, recipeDetailsViewModel,recipeSavedViewModel,userViewModel)
    }
}

@Composable
fun RecipeAppExpanded(
    navController: NavHostController = rememberNavController(),
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

    Row(modifier = Modifier.fillMaxSize()) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        if (
            currentRoute != AppRoute.LoginScreen.route &&
            currentRoute != AppRoute.RegistrationScreen.route &&
            currentRoute != AppRoute.StartScreen.route &&
            currentRoute != AppRoute.RecipeScreen.route
            ) {
            ExpandedPermanentNavigationDrawer(navController,dataStoreManager, textSize,bgColor, recipeFullViewModel = recipeFullViewModel,recipeCategoryViewModel,recipeByCategoryViewModel, recipeDetailsViewModel,recipeSavedViewModel, userViewModel)
        } else {
            NavigationGraph(navController = navController, dataStoreManager, textSize, bgColor, recipeFullViewModel,recipeCategoryViewModel,recipeByCategoryViewModel, recipeDetailsViewModel,recipeSavedViewModel, userViewModel)
        }
    }
}



@Composable
fun calculateWindowSizeClass(dp: Dp): WindowSizeClass {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    return when {
        screenWidth < 600.dp.value -> WindowSizeClass.Compact
        screenWidth < 960.dp.value -> WindowSizeClass.Medium
        else -> WindowSizeClass.Expanded
    }
}

enum class WindowSizeClass {
    Compact,
    Medium,
    Expanded
}


@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: MutableState<Float>
) {
    val starSize = 24.dp
    val starSpacing = 8.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(starSpacing)
    ) {
        repeat(maxRating) { index ->
            val isSelected = currentRating.value >= index + 1
            val rotationAngle by animateFloatAsState(
                targetValue = if (isSelected) 360f else 0f,
                animationSpec = tween(durationMillis = 1000)
            )
            val size by animateDpAsState(
                targetValue = if (isSelected) 30.dp else 24.dp,
                animationSpec = tween(durationMillis = 500)
            )

            StarIcon(
                icon = Icons.Default.Star,
                size = size,
                isSelected = isSelected,
                rotationAngle = rotationAngle,
                onClick = {
                    currentRating.value = index + 1f
                }
            )
        }
    }
}

@Composable
fun StarIcon(
    icon: ImageVector,
    size: Dp,
    isSelected: Boolean,
    rotationAngle: Float,
    onClick: () -> Unit
) {
    //val color = if (isSelected) Color.Yellow else Color.Gray
    val color = animateColorAsState(
        targetValue = if (isSelected) Color.Yellow else Color.Gray,
        animationSpec = tween(durationMillis = 500)
    )
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = color.value,
        modifier = Modifier
            .size(size)
            .rotate(rotationAngle)
            .clickable(onClick = onClick)
    )
}
@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.drawable.loading))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}


@Composable
fun BackButton(
    navController: NavHostController,
    //modifier: Modifier
) {
    Box(
        //modifier = Modifier
        //.size(60.dp)
        modifier = Modifier
            .size(45.dp)
            .background(MyTurquoise, shape = CircleShape)
    ){
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .fillMaxSize()
                //.size(20.dp)
                .clickable { navController.popBackStack() }
                .padding(10.dp)
            //.clip(CircleShape)
        )
    }
}









