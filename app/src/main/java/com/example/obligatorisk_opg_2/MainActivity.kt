package com.example.obligatorisk_opg_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.obligatorisk_opg_2.screens.EditFriendPage
import com.example.obligatorisk_opg_2.screens.EditListPage
import com.example.obligatorisk_opg_2.screens.HomePage
import com.example.obligatorisk_opg_2.screens.ListPage
import com.example.obligatorisk_opg_2.ui.theme.Obligatorisk_Opg_2Theme
import com.example.obligatorisk_opg_2.viewmodel.BirthdayViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Obligatorisk_Opg_2Theme() {
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


    NavHost(
        navController = navController,
        startDestination = NavRoutes.HomePage.route
    ) {
        composable(NavRoutes.HomePage.route) { backstackEntry ->
            HomePage(
                // Navigate to List page
                onNavigateToListPage = { navController.navigate(NavRoutes.ListPage.route) },
            )
        }
        composable(NavRoutes.ListPage.route) { backstackEntry ->
            ListPage(
                birthdayUIState = uiState,
                //Be able to navigate to both Edit Pages
                onNavigateToEditListPage = { navController.navigate(NavRoutes.EditListPage.route) },
                onNavigateToEditFriendPage = { birthday ->
                    birthdayViewModel.selectBirthday(birthday)
                    navController.navigate(NavRoutes.EditFriendPage.route)
                },
                onNavigateToHomePage = { navController.navigate(NavRoutes.HomePage.route) },
                //Navigate back to home page
                onNavigateBack = { navController.popBackStack() },
                onFilterSortChange = { query, sortBy ->
                    birthdayViewModel.filterAndSort(query, sortBy)
                }
            )
        }
        composable(NavRoutes.EditListPage.route) {
            EditListPage(
                birthdayUIState = uiState,
                //Navigate back to list page
                onNavigateToListPage = { navController.navigate(NavRoutes.ListPage.route) },
                onNavigateBack = { navController.popBackStack() },
                onBirthdayDelete = { birthday -> birthdayViewModel.deleteBirthday(birthday.id) },
                onBirthdayAdd = { birthday -> birthdayViewModel.addBirthday(birthday) }
            )

        }
        composable(NavRoutes.EditFriendPage.route) {
            EditFriendPage(
                selectedBirthday = selectedBirthday,
                onUpdateBirthday = { id, birthday -> birthdayViewModel.updateBirthday(id, birthday) },
                //Navigate back to list page
                onNavigateToListPage = { navController.navigate(NavRoutes.ListPage.route) },
                onNavigateBack = { navController.popBackStack() }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Obligatorisk_Opg_2Theme() {
        MainScreen()
    }
}