package com.example.recipeapp3.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp3.BackButton
import com.example.recipeapp3.R
import com.example.recipeapp3.data.RecipeByCategoryViewModel
import com.example.recipeapp3.data.RecipeDetailsViewModel
//import com.example.recipeapp3.RecipeListItemHorizontal
import com.example.recipeapp3.data.RecipeFull
import com.example.recipeapp3.data.RecipeFullUiState
import com.example.recipeapp3.data.RecipeFullViewModel
import com.example.recipeapp3.data.model.RecipeByCategoryDTO
import com.example.recipeapp3.data.model.RecipeSearchDTO
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.ui.theme.MyDark
import com.example.recipeapp3.ui.theme.MyDarkOrange
import com.example.recipeapp3.ui.theme.MyTurquoise
import com.example.recipeapp3.ui.theme.MyYellow
//import com.example.recipeapp3.data.recipeFullList
import kotlinx.coroutines.async
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    recipeFullUiState: RecipeFullUiState,
    textSize: MutableState<Int>,
    bgColor: MutableState<ULong>,
    searchViewModel: SearchViewModel = viewModel(),
    recipeFullViewModel: RecipeFullViewModel,
    recipeDetailsViewModel: RecipeDetailsViewModel
) {
    val searchText by searchViewModel.searchText
    LaunchedEffect(searchText) {
        val searchDeferred = async {
            delay(600)
            recipeFullViewModel.getRecipe(searchText)
        }
        searchDeferred.await()
    }


    when(recipeFullUiState){
        is RecipeFullUiState.Error -> ErrorScreen()
        is RecipeFullUiState.Success ->{
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(Color.White),
                contentPadding = PaddingValues(16.dp)
            ) {
                item(){
                    TextField(
                        value = searchText,
                        singleLine = true,
                        onValueChange = {
                            searchViewModel.setSearchText(it)
                        },
                        label = { Text("Search", fontSize = 24.sp , color = MyDark) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = MyYellow,
                            focusedBorderColor = MyDarkOrange,
                            focusedTextColor = MyDarkOrange,
                            unfocusedTextColor = Color.Black,
                            cursorColor = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items(recipeFullUiState.recipeSearchDTO) { recipe ->
                    CategoryItem(
                        navController = navController,
                        recipe = recipe,
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
fun ErrorScreen(){
    Text(text = "Помилка!!!!!!!!", fontSize = 56.sp)

}


@Composable
fun CategoryItem(
    navController: NavHostController,
    recipe: RecipeSearchDTO,
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
                recipeDetailsViewModel.currentRecipe = recipe.idRecipe
                navController.navigate(AppRoute.RecipeScreen.route)

            })
            .border(
                width = 2.dp,
                color = MyYellow,
                shape = RoundedCornerShape(8.dp)
            ),
        //elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Display category image if available
            recipe.image?.let { imageUrl ->
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
            Text(
                text = recipe.label.toString()
            )

        }
    }
}

class SearchViewModel : ViewModel() {
    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText


    fun setSearchText(text: String) {
        _searchText.value = text
    }
}