package com.example.recipeapp3.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ContentAlpha
import com.example.recipeapp3.R
//import com.example.recipeapp3.data.User
import com.example.recipeapp3.data.UserViewModel
import com.example.recipeapp3.datastore.DataStoreManager
import com.example.recipeapp3.datastore.SettingsData
import com.example.recipeapp3.db.model.User
import com.example.recipeapp3.navigation.AppRoute
import com.example.recipeapp3.ui.theme.Green
import com.example.recipeapp3.ui.theme.MyDark
import com.example.recipeapp3.ui.theme.MyLightGray
import com.example.recipeapp3.ui.theme.MyTurquoise
import com.example.recipeapp3.ui.theme.MyYellow
import com.example.recipeapp3.ui.theme.White
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

//import com.example.recipeapp3.data.currentUser




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    dataStoreManager: DataStoreManager,
    textSize: MutableState<Int>,
    bgColor: MutableState<ULong>,
    userViewModel: UserViewModel = viewModel()
) {
    val currentUser: User? = userViewModel.currentUser
    var edit by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    var firstName by remember { mutableStateOf(currentUser?.firstName) }
    var secondName by remember { mutableStateOf(currentUser?.secondName) }
    var password by remember { mutableStateOf(currentUser?.password) }
    var phone by remember { mutableStateOf(currentUser?.phone) }
    var email by remember { mutableStateOf(currentUser?.email) }
    var country by remember { mutableStateOf(currentUser?.country) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            //.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.weight(3f),
                text = "Personal info",
                fontSize = 32.sp,
                color = MyDark
            )
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { edit = !edit },

                ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    tint = if (edit) MyYellow else MyTurquoise,
                    contentDescription = ""
                )
            }

        }
        Image(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.person),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(5.dp))
        if (currentUser != null) {
            TextField(
                value = firstName ?: "" ,
                singleLine = true,
                readOnly = edit,
                onValueChange = {
                    firstName = it
                },
                label = { Text("First name", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.DarkGray,
                    focusedTextColor = MyYellow,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
            TextField(
                value = secondName ?: "" ,
                singleLine = true,
                readOnly = edit,
                onValueChange = {
                    secondName = it
                },
                label = { Text("Second name", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.DarkGray,
                    focusedTextColor = MyYellow,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )

            TextField(
                value = password ?: "" ,
                singleLine = true,
                readOnly = edit,
                onValueChange = {
                    password = it
                },
                label = { Text("Password", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.DarkGray,
                    focusedTextColor = MyYellow,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
            TextField(
                value = email ?: "" ,
                singleLine = true,
                readOnly = edit,
                onValueChange = {
                    email = it
                },
                label = { Text("Email", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.DarkGray,
                    focusedTextColor = MyYellow,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
            TextField(
                value = phone ?: "" ,
                singleLine = true,
                readOnly = edit,
                onValueChange = {
                    phone = it
                },
                label = { Text("Phone", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.DarkGray,
                    focusedTextColor = MyYellow,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
            TextField(
                value = country ?: "" ,
                singleLine = true,
                readOnly = edit,
                onValueChange = {
                    country = it
                },
                label = { Text("Country", color = Color.Black) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.DarkGray,
                    focusedTextColor = MyYellow,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
            if (!edit) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (password != null && firstName != null) {
                                userViewModel.editUser(
                                    firstName = firstName!!,
                                    password = password!!,
                                    secondName = secondName,
                                    email = email,
                                    phone = phone,
                                    country = country,
                                    photo = null
                                )

                                userViewModel.currentUser = User(
                                    firstName = firstName!!,
                                    secondName = secondName,
                                    password = password!!,
                                    email = email,
                                    phone = phone,
                                    country = country,
                                    photo = null
                                )
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
                    Text("Edit", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
