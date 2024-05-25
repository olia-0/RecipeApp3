package com.example.recipeapp3.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

import com.example.recipeapp3.WindowSizeClass
import com.example.recipeapp3.calculateWindowSizeClass
import com.example.recipeapp3.data.Recipe
import com.example.recipeapp3.data.RecipeCategoryUiState
import com.example.recipeapp3.data.RecipeCategoryViewModel
import com.example.recipeapp3.data.RecipeFullUiState
import com.example.recipeapp3.data.model.CategoriesDTO
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.ui.theme.MyYellow
import kotlinx.coroutines.async


@Composable
fun HomeScreen(
    navController: NavHostController,
    recipeCategoryUiState: RecipeCategoryUiState,
    textSize: MutableState<Int>,
    bgColor: MutableState<ULong>,
    recipeCategoryViewModel: RecipeCategoryViewModel
) {
    //val recipeList = recipeCategoryViewModel.getCategories()

    val windowSizeClass = calculateWindowSizeClass(LocalConfiguration.current.screenWidthDp.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(bgColor.value))
    ) {
        //Spacer(modifier = Modifier.width(20.dp))
        when(recipeCategoryUiState){
            is RecipeCategoryUiState.Error -> ErrorScreen()
            is RecipeCategoryUiState.Success ->
                when (windowSizeClass) {
                    WindowSizeClass.Expanded -> {

                        Row(Modifier
                            .weight(1f)
                            //.padding(50.dp)
                            ,
                            //verticalAlignment = Alignment.CenterVertically
                            ) {
                            LazyGrid(navController,recipeCategoryUiState.recipeCategoriesDTO,recipeCategoryViewModel, Modifier.weight(2f))
                            //Spacer(modifier = Modifier.width(20.dp))
                            VerticalDivider()
                            Greeting(Modifier.weight(1f),recipeCategoryViewModel)

                        }
                    }
                    else -> {
                        Column(
                            Modifier
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Categories",
                                fontSize = 32.sp,)

                            Spacer(modifier = Modifier.height(20.dp))
                            HorizontalDivider(color = MyYellow)
                            Spacer(modifier = Modifier.height(20.dp))
                            LazyGrid(navController,recipeCategoryUiState.recipeCategoriesDTO,recipeCategoryViewModel, Modifier.weight(2f))
                        }
                    }
                }

            else -> {}
        }
    }
}

@Composable
fun LazyGrid(
    navController: NavHostController,
    recipes: List<CategoriesDTO>,
    recipeCategoryViewModel: RecipeCategoryViewModel,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .testTag("recipe_grid")

    ) {
        items(recipes) { recipe ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageCategory(navController,recipe = recipe,recipeCategoryViewModel)
                Text(text = recipe.strCategory, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}


@Composable
fun ImageCategory(
    navController: NavHostController,
    recipe: CategoriesDTO,
    recipeCategoryViewModel: RecipeCategoryViewModel
) {
    Box(
        modifier = Modifier
            //.background(Color.Yellow)
            .size(150.dp)
            .fillMaxSize()
            .padding(3.dp)
            .clickable {
                recipeCategoryViewModel.currentRecipe = recipe
                navController.navigate(AppRoute.CategoryScreen.route)
            }
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(recipe.strCategoryThumb)
                .crossfade(true)
                .build(),
            contentDescription = "")
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, recipeCategoryViewModel: RecipeCategoryViewModel) {
    val currentRecipe = recipeCategoryViewModel.currentRecipe

    LazyColumn(
        modifier = modifier,
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            if (currentRecipe != null) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentRecipe.strCategory,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(350.dp)
                            .clip(RoundedCornerShape(100.dp)),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(currentRecipe.strCategoryThumb)
                            .crossfade(true)
                            .build(),
                        contentDescription = "")
                    Text(
                        text = currentRecipe.strCategoryDescription,
                        fontSize = 24.sp
                    )
                }

            } else {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ласкаво просимо до нашого додатку рецептів!",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Monospace
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Відчуйте смак життя разом з нашим додатком рецептів! Насолоджуйтесь кожним кухонним етапом та діліться смаком з близькими! Бажаємо смачних вам приготувань та незабутніх кулінарних вражень!",
                        style = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center, fontFamily = FontFamily.Monospace),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .testTag("recipe_text")
                    )
                }
            }
        }
    }
}

