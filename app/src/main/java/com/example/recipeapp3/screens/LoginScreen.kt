package com.example.recipeapp3.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.example.recipeapp3.Loader
import com.example.recipeapp3.R
import com.example.recipeapp3.RatingBar


//import com.example.recipeapp3.WindowSizeClass
//import com.example.recipeapp3.calculateWindowSizeClass

import com.example.recipeapp3.data.UserViewModel
//import com.example.recipeapp3.data.currentUser
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.ui.theme.MyDark
import com.example.recipeapp3.ui.theme.MyGray
import com.example.recipeapp3.ui.theme.MyLightGray
import com.example.recipeapp3.ui.theme.MyTurquoise
import com.example.recipeapp3.ui.theme.MyYellow
import kotlinx.coroutines.launch






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    userViewModel: UserViewModel,
    loginViewModel: LoginViewModel = viewModel()
) {
    val name by loginViewModel.nameText
    val password by loginViewModel.passwordText
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.White)

            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp)){
                        ArcBackground()
                        Column (
                            modifier = Modifier.fillMaxSize(),
                        ){
                            BackButtonSign(navController = navController)
                            Text(
                                modifier = Modifier.padding(20.dp),
                                text = "Log In",
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold,
                                color = MyYellow
                            )
                        }
                    }
                }
            }
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                        .background(MyLightGray)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MyLightGray)
                        .height(80.dp)
                        .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                        .background(MyGray)
                        //.padding(30.dp)
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(MyGray)
                    .height(90.dp)
                    .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                    .background(MyDark)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MyDark)
                        .padding(30.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(32.dp))
                        TextField(
                            value = name,
                            singleLine = true,
                            onValueChange = {
                                loginViewModel.setName(it)
                            },
                            label = { Text("First name", color = Color.White) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color.LightGray,
                                focusedBorderColor = Color.DarkGray,
                                focusedTextColor = MyYellow,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        TextField(
                            value = password,
                            singleLine = true,
                            onValueChange = {
                                loginViewModel.setPassword(it)
                            },
                            label = { Text("Password", color = Color.White) },
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color.LightGray,
                                focusedBorderColor = Color.DarkGray,
                                focusedTextColor = MyYellow,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    Log.d("NAME-PASSWORD", name + " " + password)
                                    if (userViewModel.login(name, password)) {
                                        navController.navigate(AppRoute.HomeScreen.route)
                                    } else {
                                        println("Incorrect username or password")
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MyTurquoise
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text("Log In", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

class LoginViewModel : ViewModel() {
    private val _nameText = mutableStateOf("")
    val nameText: State<String> = _nameText

    fun setName(text: String) {
        _nameText.value = text
    }

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    fun setPassword(text: String) {
        _passwordText.value = text
    }
}















/*@Composable
private fun Example7() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    var isPlaying by remember { mutableStateOf(false) }
    //var isRestart by remember { mutableStateOf(true) }
    var speedValue by remember { mutableStateOf(1f) }
    val progress by animateLottieCompositionAsState(
        composition,
        reverseOnRepeat = isPlaying,
        isPlaying = isPlaying,
        //iterations = LottieConstants.IterateForever,
        //cancellationBehavior = LottieCancellationBehavior.OnIterationFinish

    )
    LottieAnimation(
        composition,
        { progress },


//        progress =  {
//            LottieCancellationBehavior.OnIterationFinish
//        },

        //iterations = LottieConstants.IterateForever,
        // When this is true, it it will start from 0 every time it is played again.
        // When this is false, it will resume from the progress it was pause at.
        /*restartOnPlay = true,
        reverseOnRepeat = true,
        speed = speedValue,
        isPlaying = isPlaying,*/

        modifier = Modifier
            .clickable {
                Log.d("Speed",isPlaying.toString())
                //speedValue = 1f

                isPlaying = !isPlaying
                if(isPlaying == true){
                    if(speedValue == 0f){
                        speedValue = 1f
                    }
                    Log.d("Speed","Я тут")
                }
                if(isPlaying == false){
                    speedValue = 0f
                    Log.d("Speed","Я вже тут")
                }

            }

    )
}
@Composable
private fun Example6() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )
    LottieAnimation(
        composition,
        { progress },
    )
}*/
@Composable
private fun Example1() {
    val anim = rememberLottieAnimatable()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    LaunchedEffect(composition) {
        anim.animate(
            composition,
            iterations = LottieConstants.IterateForever,
        )
    }
    LottieAnimation(anim.composition, { anim.progress })
}

@Composable
private fun Example2() {
    val anim = rememberLottieAnimatable()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    var sliderGestureProgress: Float? by remember { mutableStateOf(null) }
    LaunchedEffect(composition, sliderGestureProgress) {
        when (val p = sliderGestureProgress) {
            null -> anim.animate(
                composition,
                iterations = LottieConstants.IterateForever,
                initialProgress = anim.progress,
                continueFromPreviousAnimate = false,
            )
            else -> anim.snapTo(progress = p)
        }
    }
    Box {
        LottieAnimation(anim.composition, { anim.progress })
        Slider(
            value = sliderGestureProgress ?: anim.progress,
            onValueChange = { sliderGestureProgress = it },
            onValueChangeFinished = { sliderGestureProgress = null },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )
    }
}

@Composable
private fun Example3() {
    val anim = rememberLottieAnimatable()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    var speed by remember { mutableStateOf(1f) }
    LaunchedEffect(composition, speed) {
        anim.animate(
            composition,
            iterations = LottieConstants.IterateForever,
            speed = speed,
            initialProgress = anim.progress,
        )
    }
    Box {
        LottieAnimation(composition, { anim.progress })
        Slider(
            value = speed,
            onValueChange = { speed = it },
            valueRange = -3f..3f,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .size(width = 1.dp, height = 16.dp)
                .background(Color.Black)
        )
    }
}

@Composable
private fun Example4() {
    var nonce by remember { mutableStateOf(1) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    val animatable = rememberLottieAnimatable()
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = Color.Green.toArgb(),
            keyPath = arrayOf(
                "H2",
                "Shape 1",
                "Fill 1",
            )
        ),
    )
    LaunchedEffect(composition, nonce) {
        composition ?: return@LaunchedEffect
        animatable.animate(
            composition,
            continueFromPreviousAnimate = true,
        )
    }
    LottieAnimation(
        composition,
        { animatable.progress },
        modifier = Modifier
            .clickable { nonce++ }
    )
}

@Composable
private fun Example5() {
    var shouldPlay by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    val animatable = rememberLottieAnimatable()

    LaunchedEffect(composition, shouldPlay) {
        if (composition == null || !shouldPlay) return@LaunchedEffect
        animatable.animate(composition,
            reverseOnRepeat = false,
            iteration = LottieConstants.IterateForever

        )
    }
    LottieAnimation(
        composition,
        { animatable.progress },
        modifier = Modifier
            .clickable { shouldPlay = !shouldPlay }
    )
}

@Composable
private fun Example6() {
    val anim = rememberLottieAnimatable()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    LaunchedEffect(composition) {
        anim.animate(
            composition,
            iterations = LottieConstants.IterateForever,
            reverseOnRepeat = true,
        )
    }
    LottieAnimation(anim.composition, { anim.progress })
}
