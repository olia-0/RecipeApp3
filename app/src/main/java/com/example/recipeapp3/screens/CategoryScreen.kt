package com.example.recipeapp3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp3.BackButton
import com.example.recipeapp3.data.RecipeByCategoryUiState
import com.example.recipeapp3.data.RecipeByCategoryViewModel
import com.example.recipeapp3.data.RecipeCategoryUiState
import com.example.recipeapp3.data.RecipeCategoryViewModel
import com.example.recipeapp3.data.RecipeDetailsViewModel
import com.example.recipeapp3.data.model.CategoriesDTO
import com.example.recipeapp3.data.model.RecipeByCategoryDTO
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.ui.theme.MyDark
import com.example.recipeapp3.ui.theme.MyYellow
import kotlinx.coroutines.async
import kotlinx.coroutines.delay


@Composable
fun CategoryScreen(
    navController: NavHostController,
    recipeByCategoryUiState: RecipeByCategoryUiState,
    recipeByCategoryViewModel: RecipeByCategoryViewModel,
    recipeCategoryViewModel: RecipeCategoryViewModel,
    recipeDetailsViewModel: RecipeDetailsViewModel
) {
    val currentCategory = recipeCategoryViewModel.currentRecipe?.strCategory
    LaunchedEffect(currentCategory) {
        val searchDeferred = async {
            //delay(600)
            if (currentCategory != null) {
                recipeByCategoryViewModel.getRecipeByCategory(currentCategory)
            }
        }
        searchDeferred.await()
    }
    when(recipeByCategoryUiState){
        is RecipeByCategoryUiState.Error -> ErrorScreen()
        is RecipeByCategoryUiState.Success -> {
            val categories = recipeByCategoryUiState.recipeByCategoryDTO
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                    //.padding(10.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                item(){
                   Row(
                       horizontalArrangement = Arrangement.Center
                   ) {
                       BackButton(navController = navController)
                       //Spacer(modifier = Modifier.weight(1f))
                       Row(
                           modifier = Modifier.weight(4f),
                           horizontalArrangement = Arrangement.Center
                       ) {
                           Text(
                               text = currentCategory.toString(),
                               fontSize = 32.sp,
                           )
                       }
                       Spacer(modifier = Modifier.weight(1f))


                   }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items(categories) { category ->
                    CategoryItem(
                        navController = navController,
                        category = category,
                        recipeDetailsViewModel = recipeDetailsViewModel
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        else -> {}
    }
}

@Composable
fun CategoryItem(
    navController: NavHostController,
    category: RecipeByCategoryDTO,
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
            .clickable(onClick = {
                recipeDetailsViewModel.currentRecipe = category.idRecipe
                navController.navigate(AppRoute.RecipeScreen.route)

            })
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
            category.photoRecipe?.let { imageUrl ->
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(100.dp)),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "")
                Spacer(modifier = Modifier.width(16.dp))
            }
            Text(
                text = category.nameRecipe
            )
        }
    }
}

