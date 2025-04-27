package com.example.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.model.Breed
import com.example.domain.model.BreedImage

@Composable
fun DetailScreen(modifier: Modifier = Modifier, onUrlClick: () -> Unit) {

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
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            model = breed.image.url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(modifier = Modifier.padding(4.dp), text = breed.name, fontSize = 32.sp)
            Text(modifier = Modifier.padding(4.dp), text = breed.description)
            Text(modifier = Modifier.padding(4.dp), text = "origin: ${breed.origin}")
            Text(modifier = Modifier.padding(4.dp), text = "temperament: ${breed.temperament}")
            Text(modifier = Modifier.padding(4.dp), text = "life span: ${breed.lifeSpan}")
            if (breed.wikipediaUrl.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            onUrlClick()
                        },
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(breed.wikipediaUrl)
                        }
                    }
                )
            }
        }
    }
}