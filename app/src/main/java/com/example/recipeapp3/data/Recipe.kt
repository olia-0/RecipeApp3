package com.example.recipeapp3.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.recipeapp3.R
import kotlinx.coroutines.flow.StateFlow

data class Recipe(val name: String, val imageRes: Int
)

val recipeList = listOf(
    Recipe("Паста", R.drawable.pasta2),
    Recipe("Піца", R.drawable.pizza2),
    Recipe("Салат", R.drawable.salad2),
    Recipe("Суп", R.drawable.soup2),
    Recipe("Омлет", R.drawable.omelette2),
    Recipe("Десерт", R.drawable.dessert2)
)
data class RecipeFull(val name: String, val imageRes: Int, val ingretiens: List<String>)

val recipeFullList = listOf(
    RecipeFull("Паста з грибами", R.drawable.pasta2, listOf("Паста","Гриби","Вершки")),
    RecipeFull("Піца з сиром", R.drawable.pizza2, listOf("Мука","Сир","Шинка","Помідори")),
    RecipeFull("Салат з помідорами", R.drawable.salad2,listOf("Салат","Помідори","Цибуля","Огірки")),
    RecipeFull("Гарбузевий суп ", R.drawable.soup2, listOf("Гарбуз","Вершки","Сухарі")),
    RecipeFull("Омлет з беконом", R.drawable.omelette2, listOf("Яйце","Бекон")),
    RecipeFull("Десерт з вишнею", R.drawable.dessert2, listOf("Цукор","Вишні","Яйце","Мука"))
)
/*data class User(val id: Int, val username: String, val password: String)


var userList = listOf(
    User(1,"user1", "user1"),
    User(2,"user2", "user2"),
    User(3,"user3", "user3")
)*/

