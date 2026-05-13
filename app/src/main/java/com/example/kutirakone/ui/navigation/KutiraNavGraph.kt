package com.example.kutirakone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kutirakone.ui.screens.auth.LoginScreen
import com.example.kutirakone.ui.screens.auth.RegisterScreen
import com.example.kutirakone.ui.screens.home.HomeScreen
import com.example.kutirakone.ui.screens.upload.UploadScrapScreen
import com.example.kutirakone.ui.screens.map.MapScreen
import com.example.kutirakone.ui.screens.requests.RequestsScreen
import com.example.kutirakone.ui.screens.ideas.DesignIdeasScreen
import com.example.kutirakone.ui.screens.profile.ProfileScreen
import com.example.kutirakone.ui.screens.details.ScrapDetailScreen

@Composable
fun KutiraNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onLoginSuccess = { 
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = { 
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                onNavigateToDetail = { scrapId -> navController.navigate(Screen.ScrapDetail.createRoute(scrapId)) }
            )
        }
        composable(Screen.Upload.route) {
            UploadScrapScreen(navController = navController)
        }
        composable(Screen.Map.route) {
            MapScreen(
                navController = navController,
                onNavigateToDetail = { scrapId -> navController.navigate(Screen.ScrapDetail.createRoute(scrapId)) }
            )
        }
        composable(Screen.Requests.route) {
            RequestsScreen(navController = navController)
        }
        composable(Screen.Ideas.route) {
            DesignIdeasScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                navController = navController,
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.ScrapDetail.route) { backStackEntry ->
            val scrapId = backStackEntry.arguments?.getString("scrapId") ?: ""
            ScrapDetailScreen(
                scrapId = scrapId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
