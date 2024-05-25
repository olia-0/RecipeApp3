package com.example.recipeapp3.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recipeapp3.db.UserDao
import com.example.recipeapp3.db.model.User
import kotlinx.coroutines.flow.firstOrNull


class UserViewModel(val userDao: UserDao) : ViewModel() {
    var currentUser by mutableStateOf<User?>(null)

    suspend fun login(username: String, password: String): Boolean {
        val user = userDao.getUserByUsernameAndPassword(username, password)
            .firstOrNull()
        return if (user != null) {
            currentUser = user
            true
        } else {
            false
        }
    }
    suspend fun signUp(
        firstName: String,
        password: String,
        secondName: String? = null,
        email: String? = null,
        phone: String? = null,
        country: String? = null,
        photo: String? = null,
        ) {
        val user = User(
            firstName = firstName,
            secondName = secondName,
            password = password,
            email = email,
            phone = phone,
            country = country,
            photo = photo
            )
        userDao.insertUser(user)
    }

    suspend fun editUser(
        firstName: String,
        password: String,
        secondName: String? = null,
        email: String? = null,
        phone: String? = null,
        country: String? = null,
        photo: String? = null,
    ) {
        var currentUser = currentUser ?: return
        val updatedUser = currentUser.copy(
            firstName = firstName,
            secondName = secondName,
            password = password,
            email = email,
            phone = phone,
            country = country,
            photo = photo
        )
        userDao.updateUser(updatedUser)
        currentUser = updatedUser
    }

}
