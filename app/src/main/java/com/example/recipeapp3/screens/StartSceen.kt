package com.example.recipeapp3.screens

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.ui.theme.MyDark
import com.example.recipeapp3.ui.theme.MyGray
import com.example.recipeapp3.ui.theme.MyLightGray
import com.example.recipeapp3.ui.theme.MyTurquoise
import com.example.recipeapp3.ui.theme.MyYellow
import kotlinx.coroutines.launch

@Composable
fun StartScreen(
    navController: NavHostController
){
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
                        .size(250.dp)){
                        //ArcBackground()
                        WaveAnimation()
                        Column (
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            //BackButtonSign(navController = navController)
                            Text(
                                modifier = Modifier.padding(20.dp),
                                text = "Welcome",
                                fontSize = 50.sp,
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
                        Button(
                            onClick = {
                                navController.navigate(AppRoute.LoginScreen.route)
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
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = {
                                navController.navigate(AppRoute.RegistrationScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MyTurquoise
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text("Sign Up", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WaveAnimation() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(0f, 0f)
            cubicTo(size.width  / 2 , size.height / 2 , size.width / 2 , 0f, size.width, size.height / 4)
            lineTo(size.width, 0f)
            lineTo(0f, 0f)
            close()
        }
        drawIntoCanvas { canvas ->
            canvas.drawPath(path, Paint().apply {
                color = MyTurquoise
            })
        }
    }
}