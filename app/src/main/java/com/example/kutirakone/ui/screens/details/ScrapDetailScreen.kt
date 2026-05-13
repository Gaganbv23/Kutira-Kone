package com.example.kutirakone.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.kutirakone.ui.viewmodels.RequestViewModel
import com.example.kutirakone.ui.viewmodels.ScrapViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.kutirakone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrapDetailScreen(
    scrapId: String,
    onNavigateBack: () -> Unit,
    scrapViewModel: ScrapViewModel = viewModel(),
    requestViewModel: RequestViewModel = viewModel()
) {
    val scraps by scrapViewModel.scraps.collectAsState()
    val scrap = scraps.find { it.id == scrapId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scrap Details", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.app_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.08f
            )
            if (scrap == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Scrap not found")
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = scrap.imageUrl,
                        contentDescription = "Scrap Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .background(Color.LightGray)
                    )
                    
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = scrap.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            SuggestionChip(onClick = {}, label = { Text(scrap.material) })
                            SuggestionChip(onClick = {}, label = { Text(scrap.color) })
                            SuggestionChip(onClick = {}, label = { Text(scrap.size) })
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("Description", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Beautiful ${scrap.material} scraps perfect for your next upcycling project.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
                        if (scrap.userId != currentUserId) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                OutlinedButton(
                                    onClick = { 
                                        requestViewModel.createRequest(toUser = scrap.userId, scrapId = scrap.id, type = "buy") 
                                    },
                                    modifier = Modifier.weight(1f).height(50.dp)
                                ) {
                                    Text("Request to Buy")
                                }
                                Button(
                                    onClick = { 
                                        requestViewModel.createRequest(toUser = scrap.userId, scrapId = scrap.id, type = "swap") 
                                    },
                                    modifier = Modifier.weight(1f).height(50.dp)
                                ) {
                                    Text("Request to Swap")
                                }
                            }
                        } else {
                            OutlinedButton(
                                onClick = { },
                                modifier = Modifier.fillMaxWidth().height(50.dp),
                                enabled = false
                            ) {
                                Text("This is your uploaded scrap")
                            }
                        }
                    }
                }
            }
        }
    }
}
