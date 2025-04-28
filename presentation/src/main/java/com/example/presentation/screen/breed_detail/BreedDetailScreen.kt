package com.example.presentation.screen.breed_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            model = uiState.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
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