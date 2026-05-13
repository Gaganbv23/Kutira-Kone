package com.example.kutirakone.ui.screens.ideas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kutirakone.R
import com.example.kutirakone.ui.components.BottomNavigationBar

data class Idea(val title: String, val desc: String, val imageResId: Int)

val dummyIdeas = listOf(
    Idea("Patchwork Quilt", "Combine colorful cotton squares.", R.drawable.idea_quilt),
    Idea("Handmade Masks", "Perfect use for small breathable fabrics.", R.drawable.idea_masks),
    Idea("Tote Bags", "Stitch denim scraps for durability.", R.drawable.idea_totebag),
    Idea("Fabric Dolls", "Use velvet and silk for doll dresses.", R.drawable.idea_dolls),
    Idea("Zipper Pouches", "Great for storing stationary or makeup.", R.drawable.idea_pouches),
    Idea("Scrunchies", "Easy 10-minute project for silk strips.", R.drawable.idea_scrunchies)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignIdeasScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Design Ideas", style = MaterialTheme.typography.titleLarge) },
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
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 16.dp,
                modifier = Modifier.fillMaxSize()
            ) {
                items(dummyIdeas) { idea ->
                    IdeaCard(idea)
                }
            }
        }
    }
}

@Composable
fun IdeaCard(idea: Idea) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Image(
                painter = painterResource(id = idea.imageResId),
                contentDescription = idea.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 150.dp, max = 250.dp)
                    .background(Color.LightGray)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = idea.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = idea.desc,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
