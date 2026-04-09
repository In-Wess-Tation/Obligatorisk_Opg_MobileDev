package com.example.obligatorisk_opg_2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.obligatorisk_opg_2.data.Birthday
import com.example.obligatorisk_opg_2.screens.ListPage

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.obligatorisk_opg_2", appContext.packageName)
    }

    @Test
    fun friendListErrorMessageTest() {
        composeTestRule.setContent {
            val birthdayUIState = BirthdayUIState (
                isLoading = false,
                birthdays = emptyList(), error = "Error message"
            )
            ListPage(birthdayUIState = birthdayUIState)
        }

        composeTestRule.onNodeWithText("Problem", substring = true).assertIsDisplayed()
    }

    @Test
    fun isThereAnyFriends() {
        val friend1 = Birthday(
            id = 5,
            userId = "testuser4@gmail.com",
            name = "Gertrod",
            birthYear = 2002,
            birthMonth = 3,
            birthDayOfMonth = 15,
            remarks = "Bla Bla Bla",
            age = 24
        )
        val friend2 = Birthday(
            id = 5,
            userId = "testuser4@gmail.com",
            name = "Bente",
            birthYear = 2001,
            birthMonth = 5,
            birthDayOfMonth = 16,
            remarks = "Prosit",
            age = 24
        )
        val friend3 = Birthday(
            id = 5,
            userId = "testuser4@gmail.com",
            name = "Conrad",
            birthYear = 2000,
            birthMonth = 6,
            birthDayOfMonth = 17,
            remarks = "Hvaså",
            age = 24
        )
        val birthdayUIState = BirthdayUIState(
            isLoading = false,
            birthdays = listOf(friend1, friend2, friend3),
            error = null
        )
        composeTestRule.setContent {
            ListPage(birthdayUIState = birthdayUIState)
        }

        composeTestRule.onNodeWithText("Problem", substring = true).assertDoesNotExist()

        composeTestRule.onNodeWithText("friend1").assertIsDisplayed()
        composeTestRule.onNodeWithText("friend2").assertIsDisplayed()
        composeTestRule.onNodeWithText("friend3", substring = true).assertIsDisplayed()

    }
}