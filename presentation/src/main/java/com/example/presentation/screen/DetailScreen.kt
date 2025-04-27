package com.example.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.model.Breed
import com.example.domain.model.BreedImage

@Composable
fun DetailScreen(modifier: Modifier = Modifier) {

    val breed = Breed(
        name = "Abyssinian",
        origin = "Egypt",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        lifeSpan = "14 - 15",
        image = BreedImage(
            id = "0XYvRd7oD",
            url = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        ),
        id = "abys",
        wikipediaUrl = "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
    )
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = breed.image.url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(modifier = Modifier.padding(4.dp), text = "name: ${breed.name}")
        Text(modifier = Modifier.padding(4.dp), text = "origin: ${breed.origin}")
        Text(modifier = Modifier.padding(4.dp), text = "description: ${breed.description}")
        Text(modifier = Modifier.padding(4.dp), text = "temperament: ${breed.temperament}")
        Text(modifier = Modifier.padding(4.dp), text = "life span: ${breed.lifeSpan}")
    }

}