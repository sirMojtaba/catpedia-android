package com.example.presentation.screen.breed_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun BreedDetailScreen(
    viewModel: BreedDetailViewModel = hiltViewModel(),
    onUrlClick: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                model = uiState.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { viewModel.toggleFavorite(uiState.id) },
                modifier = Modifier
                    .padding(12.dp)
                    .size(40.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = if (uiState.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (uiState.isFavorite) Color.Red else Color.Gray
                )
            }
        }
        Column(modifier = Modifier.padding(8.dp)) {
            Text(modifier = Modifier.padding(4.dp), text = uiState.name, fontSize = 32.sp)
            Text(modifier = Modifier.padding(4.dp), text = uiState.description)
            Text(modifier = Modifier.padding(4.dp), text = "origin: ${uiState.origin}")
            Text(modifier = Modifier.padding(4.dp), text = "temperament: ${uiState.temperament}")
            Text(modifier = Modifier.padding(4.dp), text = "life span: ${uiState.lifeSpan}")
            if (uiState.wikipediaUrl.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            onUrlClick(uiState.wikipediaUrl)
                        },
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(uiState.wikipediaUrl)
                        }
                    }
                )
            }
        }
    }
}