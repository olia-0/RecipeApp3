package com.example.recipeapp3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp3.data.RecipeDetailsViewModel
import com.example.recipeapp3.data.RecipeFullUiState
import com.example.recipeapp3.data.SavedRecipeUiState
import com.example.recipeapp3.data.SavedRecipeViewModel
import com.example.recipeapp3.data.model.RecipeDTO
import com.example.recipeapp3.data.model.RecipeSearchDTO
import com.example.recipeapp3.db.model.SavedRecipe
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.ui.theme.MyDark
import com.example.recipeapp3.ui.theme.MyYellow
import kotlinx.coroutines.flow.Flow

@Composable
fun SavedScreen(
    navController: NavHostController,
    recipeSavedUiState: SavedRecipeUiState,
    recipeDetailsViewModel: RecipeDetailsViewModel
    //savedRecipes: Flow<List<SavedRecipe>>,
    //savedRecipeViewModel: SavedRecipeViewModel
) {

    when (recipeSavedUiState) {
        is SavedRecipeUiState.Error -> ErrorScreen()
        is SavedRecipeUiState.Success -> {
            SavedRecipeList(
                navController = navController,
                savedRecipes = recipeSavedUiState.recipeSaved,
                recipeDetailsViewModel = recipeDetailsViewModel
            )
        }

        else -> {}
    }
}

@Composable
fun SavedRecipeList(
    navController: NavHostController,
    savedRecipes: Flow<List<SavedRecipe>>,
    recipeDetailsViewModel: RecipeDetailsViewModel
) {
    val recipes = savedRecipes.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(recipes.value) { recipe ->
            SavedRecipeItem(
                navController = navController,
                recipe = recipe,
                recipeDetailsViewModel
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SavedRecipeItem(
    navController: NavHostController,
    recipe: SavedRecipe,
    recipeDetailsViewModel: RecipeDetailsViewModel
) {
    Card(
        colors = CardColors(
            containerColor = Color.White,
            contentColor = MyDark,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.LightGray
        ) ,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                recipeDetailsViewModel.currentRecipe = recipe.recipeid
                navController.navigate(AppRoute.RecipeScreen.route)
            }
            .border(
                width = 2.dp,
                color = MyYellow,
                shape = RoundedCornerShape(8.dp)
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            recipe.recipephoto?.let { imageUrl ->
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(100.dp)),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "")
                Spacer(modifier = Modifier.width(16.dp))
            }
            Text(text = recipe.recipename)
        }
    }
}