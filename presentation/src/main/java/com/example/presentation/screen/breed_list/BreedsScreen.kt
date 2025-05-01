package com.example.presentation.screen.breed_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Breed
import com.example.presentation.screen.BreedItem
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BreedsScreen(
    viewModel: BreedsViewModel = hiltViewModel(),
    onBreedClick: (Breed) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val pullToRefreshState =
        rememberPullRefreshState(refreshing = uiState.isRefreshing, onRefresh = {
            viewModel.refreshBreeds()
        })

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                if (lastVisibleItem != null && lastVisibleItem.index >= uiState.breeds.lastIndex && searchQuery.isBlank()) {
                    viewModel.getBreeds()
                }
            }
    }

    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }
            .debounce { 300L }
            .distinctUntilChanged()
            .collect { query ->
                viewModel.onSearchQueryChanged(query)
            }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let { errorMessage ->
            snackbarHostState.showSnackbar(errorMessage)
            viewModel.consumeError()
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            //Search bar
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search...") },
                singleLine = true
            )

            //Breeds list
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullToRefreshState)
            ) {
                LazyColumn(modifier = Modifier.padding(4.dp), state = listState) {
                    itemsIndexed(
                        items = if (searchQuery.isBlank()) uiState.breeds else uiState.filteredBreeds,
                        key = { _, item -> item.id }
                    ) { index, item ->
                        BreedItem(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .clickable { onBreedClick(item) },
                            breed = item,
                            isFavorite = item.isFavorite,
                            onFavoriteClick = { viewModel.toggleFavorite(item) }
                        )
                    }

                    if (uiState.isLoading && uiState.hasMore && searchQuery.isBlank()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
                PullRefreshIndicator(
                    refreshing = uiState.isRefreshing,
                    state = pullToRefreshState,
                    modifier = Modifier.align(
                        Alignment.TopCenter
                    )
                )
            }


            Box(modifier = Modifier.fillMaxSize()) {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }
        }
    }
}