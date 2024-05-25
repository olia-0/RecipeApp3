package com.example.recipeapp3.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.example.recipeapp3.BackButton
import com.example.recipeapp3.R
import com.example.recipeapp3.data.RecipeByCategoryUiState
import com.example.recipeapp3.data.RecipeByCategoryViewModel
import com.example.recipeapp3.data.RecipeDetailsUiState
import com.example.recipeapp3.data.RecipeDetailsViewModel
import com.example.recipeapp3.data.SavedRecipeViewModel
import com.example.recipeapp3.data.model.RecipeDTO
import com.example.recipeapp3.db.model.SavedRecipe
import com.example.recipeapp3.ui.theme.MyTurquoise
import com.example.recipeapp3.ui.theme.MyYellow
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen (
    navController: NavHostController,
    recipeDetailsUiState: RecipeDetailsUiState,
    recipeDetailsViewModel: RecipeDetailsViewModel,
    recipeByCategoryViewModel: RecipeByCategoryViewModel,
    recipeSavedViewModel: SavedRecipeViewModel
){
    //val currentRecipeId = recipeByCategoryViewModel.currentRecipeByCategory?.idRecipe
    val currentRecipeId: String? = recipeDetailsViewModel.currentRecipe
        LaunchedEffect(currentRecipeId) {
        val searchDeferred = async {
            //delay(600)
            if (currentRecipeId != null) {
                recipeDetailsViewModel.getRecipeDetails(currentRecipeId)
            }
        }
        searchDeferred.await()
    }
    when(recipeDetailsUiState){
        is RecipeDetailsUiState.Error -> ErrorScreen()
        is RecipeDetailsUiState.Success -> {
            val recipe = recipeDetailsUiState.recipeDTO
            val scaffoldState = rememberBottomSheetScaffoldState()
            val scope = rememberCoroutineScope()

            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContent = {
                    LazyColumn(
                        modifier = Modifier.padding(10.dp)
                    ){
                        item {
                            Text(
                                text = recipe[0].nameRecipe,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = recipe[0].tagsRecipe.toString(),
                                modifier = Modifier.padding(5.dp)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = recipe[0].categoryRecipe.toString(),
                                    modifier = Modifier.padding(5.dp)
                                )
                                //Spacer(modifier = Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(10.dp)
                                        .background(Color.LightGray, shape = CircleShape)


                                )
                                Text(
                                    text = recipe[0].areaRecipe.toString(),
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(color = MyYellow)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Ingredients",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                                )
                            Spacer(modifier = Modifier.height(10.dp))
                            Column {
                                RowIngredient(recipe[0].strIngredient1,recipe[0].strMeasure1)
                                RowIngredient(recipe[0].strIngredient2,recipe[0].strMeasure2)
                                RowIngredient(recipe[0].strIngredient3,recipe[0].strMeasure3)
                                RowIngredient(recipe[0].strIngredient4,recipe[0].strMeasure4)
                                RowIngredient(recipe[0].strIngredient5,recipe[0].strMeasure5)
                                RowIngredient(recipe[0].strIngredient6,recipe[0].strMeasure6)
                                RowIngredient(recipe[0].strIngredient7,recipe[0].strMeasure7)
                                RowIngredient(recipe[0].strIngredient8,recipe[0].strMeasure8)
                                RowIngredient(recipe[0].strIngredient9,recipe[0].strMeasure9)
                                RowIngredient(recipe[0].strIngredient10,recipe[0].strMeasure10)
                            }
                            Spacer(modifier = Modifier.height(10.dp))



                            HorizontalDivider(color = MyYellow)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Instructions",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                //modifier = Modifier.padding(5.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = recipe[0].instructionsRecipe.toString()
                            )
                                    
                            
                        }
                    }

                },
                sheetPeekHeight = 570.dp,
                sheetContainerColor = Color.White,
                sheetContentColor = Color.DarkGray,
                sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                //sheetContentColor = Color.Black,
                //sheetElevation = 8.dp,
                content = {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        //.clip(RoundedCornerShape(100.dp)),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(recipe[0].photoRecipe)
                            .crossfade(true)
                            .build(),
                        contentDescription = ""
                    )
                    Row(

                        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        BackButton(navController = navController)
                        ToggleProperty(recipe[0],recipeSavedViewModel)

                    }
                    

                }
            )

        }
        else -> {}
    }
}

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet() {

    ModalBottomSheet(
        modifier = Modifier,
        sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
        ),
        onDismissRequest = {},
        shape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp
    ),
        ) {
        CustomBottomSheetContainer()
    }
}
@Composable
fun CustomBottomSheetContainer() {
    Scaffold(topBar = {
        Column {
            Text(text = "Theme", modifier = Modifier
                .height(75.dp)
                .padding(start = 29.dp, top = 26.dp),fontSize = 23.sp)
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Select theme", modifier = Modifier
                .padding(start = 29.dp, top = 20.dp, bottom = 10.dp)
                .height(40.dp),fontSize = 20.sp)

        }
    }
}

@Composable
fun RowIngredient(
    ingredient: String?,
    measure: String?
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            ingredient?.let { Text(text = it) }
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            measure?.let { Text(text = it) }
        }
    }
}

@Composable
fun ToggleProperty(
    recipe: RecipeDTO,
    recipeSavedViewModel: SavedRecipeViewModel
) {
    //val currentRecipeId: String? = recipeDetailsViewModel.currentRecipe

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    var useDynamicProperty by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(recipe.idRecipe) {
        val searchDeferred = async {
            //delay(600)
            if (recipe.idRecipe != null) {
                val savedRecipe = recipeSavedViewModel.getSavedRecipeById(recipe.idRecipe)
                useDynamicProperty = savedRecipe != null
            }
        }
        searchDeferred.await()
    }
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = Color.Red.toArgb(),
            keyPath = arrayOf(
                "H2",
                "Shape 1",
                "Fill 1",
            )
        ),
    )
    val dynamicP = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = Color.Gray.toArgb(),
            keyPath = arrayOf(
                "H2",
                "Shape 1",
                "Fill 1",
            )
        ),
    )
    LottieAnimation(
        composition,
        contentScale = ContentScale.Fit,
        isPlaying = useDynamicProperty,
        //iterations = LottieConstants.IterateForever,
        dynamicProperties = dynamicProperties.takeIf { useDynamicProperty } ?: dynamicP,
        modifier = Modifier
            .background(MyTurquoise, shape = CircleShape)
            .size(45.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    useDynamicProperty = !useDynamicProperty
                    if (useDynamicProperty == true){
                        coroutineScope.launch {
                            if (recipeSavedViewModel.getSavedRecipeById(recipe.idRecipe)  == null){
                                recipeSavedViewModel.insertRecipe(SavedRecipe(recipeid = recipe.idRecipe, recipename = recipe.nameRecipe, recipephoto = recipe.photoRecipe)
                                )

                            }
                        }
                    }else{
                        coroutineScope.launch {
                            if (recipeSavedViewModel.getSavedRecipeById(recipe.idRecipe) != null) {
                                recipeSavedViewModel.deleteRecipeById(recipeid = recipe.idRecipe)

                            }
                        }
                    }


                },
            )
    )
}