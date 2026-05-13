package com.example.kutirakone.ui.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kutirakone.ui.components.BottomNavigationBar
import com.example.kutirakone.ui.viewmodels.ScrapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    onNavigateToDetail: (String) -> Unit,
    viewModel: ScrapViewModel = viewModel()
) {
    val scraps by viewModel.scraps.collectAsState()
    
    val myLocation = LatLng(12.9716, 77.5946) 
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myLocation, 12f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nearby Scraps", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        GoogleMap(
            modifier = Modifier.padding(padding).fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            scraps.forEach { scrap ->
                Marker(
                    state = MarkerState(position = LatLng(scrap.latitude, scrap.longitude)),
                    title = scrap.title,
                    snippet = "${scrap.material} - Click to view",
                    onClick = {
                        onNavigateToDetail(scrap.id)
                        true
                    }
                )
            }
        }
    }
}
