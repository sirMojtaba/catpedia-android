package com.example.presentation.screen.breed_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.presentation.screen.breed_detail.model.BreedUiAction
import com.example.presentation.screen.breed_detail.model.BreedUiEvent

@Composable
fun BreedDetailRoute(
    viewModel: BreedDetailViewModel = hiltViewModel(),
    onUrlClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is BreedUiEvent.Navigation.WikipediaScreen -> onUrlClick(uiEvent.wikipediaUrl)
            }
        }
    }

    BreedDetailScreen(
        id = uiState.id,
        name = uiState.name,
        origin = uiState.origin,
        description = uiState.description,
        temperament = uiState.temperament,
        lifeSpan = uiState.lifeSpan,
        imageUrl = uiState.imageUrl,
        wikipediaUrl = uiState.wikipediaUrl,
        isFavorite = uiState.isFavorite,
        onAction = viewModel::onAction
    )
}

@Composable
fun BreedDetailScreen(
    id: String,
    name: String,
    origin: String,
    description: String,
    temperament: String,
    lifeSpan: String,
    imageUrl: String,
    wikipediaUrl: String,
    isFavorite: Boolean,
    onAction: (BreedUiAction) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { onAction(BreedUiAction.ToggleFavorite(id)) },
                modifier = Modifier
                    .padding(12.dp)
                    .size(40.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
        Column(modifier = Modifier.padding(8.dp)) {
            Text(modifier = Modifier.padding(4.dp), text = name, fontSize = 32.sp)
            Text(
                modifier = Modifier.padding(4.dp),
                text = description,
                fontSize = 14.sp,
                lineHeight = 16.sp
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = "origin: $origin",
                fontSize = 14.sp,
                lineHeight = 16.sp
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = "temperament: $temperament",
                fontSize = 14.sp,
                lineHeight = 16.sp
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = "life span: $lifeSpan",
                fontSize = 14.sp,
                lineHeight = 16.sp
            )
            if (wikipediaUrl.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            onAction(BreedUiAction.Navigation.WikipediaScreen(wikipediaUrl))
                        },
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(wikipediaUrl)
                        }
                    }
                )
            }
        }
    }
}