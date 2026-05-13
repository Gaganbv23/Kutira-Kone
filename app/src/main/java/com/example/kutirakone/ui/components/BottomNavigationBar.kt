package com.example.kutirakone.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kutirakone.ui.navigation.BottomNavItems
import com.example.kutirakone.ui.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        
        BottomNavItems.forEach { screen ->
            val icon = getIconForScreen(screen)
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun getIconForScreen(screen: Screen): ImageVector {
    return when(screen) {
        Screen.Home -> Icons.Default.Home
        Screen.Map -> Icons.Default.LocationOn
        Screen.Upload -> Icons.Default.AddCircle
        Screen.Requests -> Icons.Default.Email
        Screen.Ideas -> Icons.Default.Lightbulb
        Screen.Profile -> Icons.Default.Person
        else -> Icons.Default.Home
    }
}
