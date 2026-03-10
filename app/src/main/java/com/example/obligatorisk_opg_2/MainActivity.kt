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

    NavHost(
        navController = navController,
        startDestination = NavRoutes.HomePage.route
    ) {
        composable(NavRoutes.HomePage.route) { backstackEntry ->
            HomePage(
                // Navigate to List page
                onNavigateToListPage = { navController.navigate(NavRoutes.ListPage.route) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(NavRoutes.ListPage.route) { backstackEntry ->
            ListPage(
                //Be able to navigate to both Edit Pages
                onNavigateToEditListPage = { navController.navigate(NavRoutes.EditListPage.route) },
                onNavigateToEditFriendPage = { navController.navigate(NavRoutes.EditFriendPage.route) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(NavRoutes.EditListPage.route) {
            EditListPage(
                //Navigate back to list page
                onNavigateToListPage = { navController.navigate(NavRoutes.ListPage.route) },
                onNavigateBack = { navController.popBackStack() }
            )

        }
        composable(NavRoutes.EditFriendPage.route) {
            EditFriendPage(
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