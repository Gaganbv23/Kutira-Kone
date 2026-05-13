package com.example.kutirakone.ui.screens.requests

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kutirakone.ui.components.BottomNavigationBar

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kutirakone.ui.viewmodels.RequestViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.kutirakone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestsScreen(
    navController: NavController,
    viewModel: RequestViewModel = viewModel()
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Incoming", "My Requests")

    val incomingRequests by viewModel.incomingRequests.collectAsState()
    val myRequests by viewModel.myRequests.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trade Requests", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.app_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.08f
            )
            Column(modifier = Modifier.fillMaxSize()) {
                TabRow(selectedTabIndex = selectedTabIndex) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(title) }
                        )
                    }
                }
                
                if (selectedTabIndex == 0) {
                    if (incomingRequests.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No incoming requests yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    } else {
                        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(incomingRequests.size) { index ->
                                val req = incomingRequests[index]
                                IncomingRequestCard("User wants to ${req.type} for Scrap #${req.scrapId}")
                            }
                        }
                    }
                } else {
                    if (myRequests.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("You haven't made any requests yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    } else {
                        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(myRequests.size) { index ->
                                val req = myRequests[index]
                                MyRequestCard("You requested to ${req.type} Scrap #${req.scrapId}", req.status)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun IncomingRequestCard(text: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Row {
                IconButton(onClick = {}) { Icon(Icons.Default.Check, contentDescription = "Accept", tint = MaterialTheme.colorScheme.primary) }
                IconButton(onClick = {}) { Icon(Icons.Default.Close, contentDescription = "Reject", tint = MaterialTheme.colorScheme.error) }
            }
        }
    }
}

@Composable
fun MyRequestCard(text: String, status: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Badge { Text(status) }
        }
    }
}
