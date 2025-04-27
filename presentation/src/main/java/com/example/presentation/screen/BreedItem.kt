package com.example.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.model.Breed

@Composable
fun BreedItem(modifier: Modifier = Modifier, breed: Breed) {
    Row(
        modifier = modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = breed.image.url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(start = 0.dp)
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = breed.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(modifier = Modifier.padding(4.dp), text = "origin: ${breed.origin}")
            Text(modifier = Modifier.padding(4.dp), text = "temperament: ${breed.temperament}")
        }
    }
}