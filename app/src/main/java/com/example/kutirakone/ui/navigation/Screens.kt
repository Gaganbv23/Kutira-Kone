package com.example.kutirakone.ui.navigation

sealed class Screen(val route: String, val title: String = "") {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home", "Home")
    object Upload : Screen("upload", "Upload")
    object Map : Screen("map", "Map")
    object Requests : Screen("requests", "Requests")
    object Ideas : Screen("ideas", "Ideas")
    object Profile : Screen("profile", "Profile")
    object ScrapDetail : Screen("scrap_detail/{scrapId}") {
        fun createRoute(scrapId: String) = "scrap_detail/$scrapId"
    }
}

val BottomNavItems = listOf(
    Screen.Home,
    Screen.Map,
    Screen.Upload,
    Screen.Requests,
    Screen.Ideas,
    Screen.Profile
)
