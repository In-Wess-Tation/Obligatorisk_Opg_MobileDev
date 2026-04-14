package com.example.obligatorisk_opg_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.obligatorisk_opg_2.screens.EditFriendPage
import com.example.obligatorisk_opg_2.screens.EditListPage
import com.example.obligatorisk_opg_2.screens.HomePage
import com.example.obligatorisk_opg_2.screens.ListPage
import com.example.obligatorisk_opg_2.ui.theme.Obligatorisk_Opg_2Theme
import com.example.obligatorisk_opg_2.viewmodel.AuthViewModel
import com.example.obligatorisk_opg_2.viewmodel.BirthdayViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Obligatorisk_Opg_2Theme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val birthdayViewModel: BirthdayViewModel = koinViewModel()
    val uiState by birthdayViewModel.birthdayUIState.collectAsState()
    val selectedBirthday by birthdayViewModel.selectedBirthday.collectAsState()
    val authViewModel: AuthViewModel = viewModel()

    // Handle Navigation based on Auth State
    LaunchedEffect(authViewModel.user) {
        if (authViewModel.user != null) {
            // User just logged in: Go to List
            navController.navigate(NavRoutes.ListPage.route) {
                popUpTo(NavRoutes.HomePage.route) { inclusive = true }
            }
            // Fetch birthdays for the logged-in user
            authViewModel.user?.email?.let { email ->
                birthdayViewModel.getBirthdays(email)
            }
        } else {
            // User logged out: Go to Home
            navController.navigate(NavRoutes.HomePage.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = NavRoutes.HomePage.route
    ) {
        composable(NavRoutes.HomePage.route) {
            HomePage(
                message = authViewModel.message,
                onLogin = { email, password -> authViewModel.signIn(email, password) },
                onRegister = { email, password -> authViewModel.register(email, password) }
            )
        }
        composable(NavRoutes.ListPage.route) {
            ListPage(
                userEmail = authViewModel.user?.email ?: authViewModel.user?.uid,
                birthdayUIState = uiState,
                onNavigateToEditListPage = { navController.navigate(NavRoutes.EditListPage.route) },
                onNavigateToEditFriendPage = { birthday ->
                    birthdayViewModel.selectBirthday(birthday)
                    navController.navigate(NavRoutes.EditFriendPage.route)
                },
                onNavigateToHomePage = { /* Handled by LaunchedEffect */ },
                onLogOut = { authViewModel.signOut() },
                onFilterSortChange = { query, sortBy ->
                    birthdayViewModel.filterAndSort(query, sortBy)
                }
            )
        }
        composable(NavRoutes.EditListPage.route) {
            EditListPage(
                birthdayUIState = uiState,
                userId = authViewModel.user?.email ?: authViewModel.user?.uid ?: "",
                onNavigateToListPage = { navController.navigate(NavRoutes.ListPage.route) },
                onNavigateBack = { navController.popBackStack() },
                onBirthdayDelete = { id, userId -> 
                    birthdayViewModel.deleteBirthday(id, userId) 
                },
                onBirthdayAdd = { birthday -> birthdayViewModel.addBirthday(birthday) }
            )
        }
        composable(NavRoutes.EditFriendPage.route) {
            EditFriendPage(
                selectedBirthday = selectedBirthday,
                onUpdateBirthday = { id, birthday -> birthdayViewModel.updateBirthday(id, birthday) },
                onNavigateToListPage = { navController.navigate(NavRoutes.ListPage.route) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Obligatorisk_Opg_2Theme {
        MainScreen()
    }
}
