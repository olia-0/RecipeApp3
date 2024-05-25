package com.example.recipeapp3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp3.db.model.User

@Database(entities = [User::class], version = 2)
abstract class UsersDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var Instance: UsersDatabase? = null
        fun getDatabase(context: Context): UsersDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "User.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}