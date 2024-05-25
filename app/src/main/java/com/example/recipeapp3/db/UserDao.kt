package com.example.recipeapp3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.recipeapp3.db.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE first_name = :username AND password = :password LIMIT 1")
    fun getUserByUsernameAndPassword(username: String, password: String): Flow<User?>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId: Int): Flow<User?>

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}