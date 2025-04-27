package com.example.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Breed
import com.example.presentation.viewmodel.BreedsViewModel

@Composable
fun BreedsScreen(
    modifier: Modifier = Modifier,
    viewModel: BreedsViewModel = hiltViewModel(),
    onBreedClick: (Breed) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(4.dp)) {
            itemsIndexed(uiState.breeds) { index, item ->
                BreedItem(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clickable {
                            onBreedClick(item)
                        },
                    breed = item
                )
            }
        }
    }
}