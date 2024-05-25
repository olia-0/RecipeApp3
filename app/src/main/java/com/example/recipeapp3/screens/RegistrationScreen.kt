package com.example.recipeapp3.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.recipeapp3.BackButton
import com.example.recipeapp3.data.UserViewModel
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.ui.theme.MyDark
import com.example.recipeapp3.ui.theme.MyTurquoise
import com.example.recipeapp3.ui.theme.MyYellow
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavHostController,
    userViewModel: UserViewModel,
    registrationViewModel: RegistrationViewModel = viewModel()
) {
    val name by registrationViewModel.nameText
    val password by registrationViewModel.passwordText
    val passwordConfirm by registrationViewModel.passwordConfirmText
    val email by registrationViewModel.emailText
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
                        .height(300.dp),
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
                                text = "Sign Up",
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold,
                                color = MyYellow
                            )
                        }
                    }
                }
            }
            //Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                    .background(MyDark)
                    .padding(40.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(32.dp))
                    TextField(
                        value = name,
                        singleLine = true,
                        onValueChange = {
                            registrationViewModel.setName(it)
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
                            registrationViewModel.setPassword(it)
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
                    TextField(
                        value = passwordConfirm,
                        singleLine = true,
                        onValueChange = {
                            registrationViewModel.setPasswordConfirm(it)
                        },
                        label = { Text("Confirm password", color = Color.White) },
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
                    TextField(
                        value = email,
                        singleLine = true,
                        onValueChange = {
                            registrationViewModel.setEmail(it)
                        },
                        label = { Text("Email", color = Color.White) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.LightGray,
                            focusedBorderColor = Color.DarkGray,
                            cursorColor = Color.White,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                if (registrationViewModel.validatePassword()) {
                                    userViewModel?.signUp(name, password)
                                    if (userViewModel?.login(name, password) == true) {
                                        navController?.navigate("LoginScreen")
                                    } else {
                                        println("Incorrect username or password")
                                    }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MyTurquoise),
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

@Composable
fun ArcBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val arcWidth = size.width * 1.2f
        val arcHeight = size.height * 1.6f

        drawArc(
            color = MyTurquoise,
            startAngle = 0f,
            sweepAngle = 90f,
            useCenter = true,
            topLeft = Offset(-arcWidth / 2, -arcHeight / 2),
            size = Size(arcWidth, arcHeight)
        )
    }
}


@Composable
fun BackButtonSign(
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            //.background(Color.LightGray, shape = CircleShape)
    ){
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .fillMaxSize()
                .clickable { navController.popBackStack() }
                .padding(10.dp)
        )
    }
}

class RegistrationViewModel : ViewModel() {
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

    private val _passwordConfirmText = mutableStateOf("")
    val passwordConfirmText: State<String> = _passwordConfirmText

    fun setPasswordConfirm(text: String) {
        _passwordConfirmText.value = text
    }

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    fun setEmail(text: String) {
        _emailText.value = text
    }

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun validatePassword(): Boolean {
        return if (_passwordText.value == _passwordConfirmText.value) {
            setErrorMessage("")
            true
        } else {
            setErrorMessage("Passwords do not match")
            false
        }
    }
}







@Composable
fun GetWeatherApp() {
    val backgroundColor = listOf(Color(0xFF2078EE), Color(0xFF74E6FE))
    val sunColor = listOf(Color(0xFFFFC200), Color(0xFFFFE100))
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {
        val width = size.width
        val height = size.height
        val path = Path().apply {
            moveTo(width.times(.76f), height.times(.72f))
            cubicTo(
                width.times(.93f),
                height.times(.72f),
                width.times(.98f),
                height.times(.41f),
                width.times(.76f),
                height.times(.40f)
            )
            cubicTo(
                width.times(.75f),
                height.times(.21f),
                width.times(.35f),
                height.times(.21f),
                width.times(.38f),
                height.times(.50f)
            )
            cubicTo(
                width.times(.25f),
                height.times(.50f),
                width.times(.20f),
                height.times(.69f),
                width.times(.41f),
                height.times(.72f)
            )
            close()
        }
        drawRoundRect(
            brush = Brush.verticalGradient(backgroundColor),
            cornerRadius = CornerRadius(50f, 50f),

            )
        drawCircle(
            brush = Brush.verticalGradient(sunColor),
            radius = width.times(.17f),
            center = Offset(width.times(.35f), height.times(.35f))
        )
        drawPath(path = path, color = Color.White.copy(alpha = .90f))
    }
}
