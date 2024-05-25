package com.example.recipeapp3

import com.example.recipeapp3.data.User
import com.example.recipeapp3.data.UserViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class UserViewTest {
    private lateinit var userViewModel: UserViewModel

    @Before
    fun setUp() {
        userViewModel = UserViewModel()
    }

    @Test
    fun testAddUser() {
        val user = User(6,"user6", "user6")
        userViewModel.addUser(user)
        TestCase.assertEquals(user, userViewModel.findUserByUsernameAndPassword("user6", "user6"))
    }

    @Test
    fun testFindUserByUsernameAndPassword() {
        val user = User(7,"user7", "user7")
        userViewModel.addUser(user)
        TestCase.assertEquals(user, userViewModel.findUserByUsernameAndPassword("user7", "user7"))
        TestCase.assertNull(userViewModel.findUserByUsernameAndPassword("user7", "user"))
        TestCase.assertNull(userViewModel.findUserByUsernameAndPassword("user", "user7"))
    }

    @Test
    fun testGetUserById() {
        val user = User(8,"user8", "user8")
        userViewModel.addUser(user)
        userViewModel.login("user8", "user8")
        TestCase.assertEquals(user, userViewModel.getUserById(user.id.toString()))
        TestCase.assertNull(userViewModel.getUserById("invalidId"))
    }

    @Test
    fun testIsUserExists() {
        val user = User(9,"user9", "user9")
        userViewModel.addUser(user)
        TestCase.assertTrue(userViewModel.isUserExists("user9"))
        TestCase.assertFalse(userViewModel.isUserExists("invalidUser"))
    }

    @Test
    fun testLogin() {
        val user = User(5,"user5", "user5")
        userViewModel.addUser(user)
        TestCase.assertTrue(userViewModel.login("user5", "user5"))
        TestCase.assertFalse(userViewModel.login("user5", "user"))
        TestCase.assertFalse(userViewModel.login("user", "user5"))
    }

    @Test
    fun testLogout() {
        val user = User(10,"user10", "user10")
        userViewModel.addUser(user)
        userViewModel.login("user10", "user10")
        userViewModel.logout()
        TestCase.assertNull(userViewModel.currentUser)
    }
}