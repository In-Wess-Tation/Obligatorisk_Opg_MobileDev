package com.example.obligatorisk_opg_2

sealed class NavRoutes(val route: String) {
    data object HomePage : NavRoutes("home")
    data object ListPage : NavRoutes("list")
    data object EditListPage : NavRoutes("editList")
    data object EditFriendPage : NavRoutes("editFriend")
}