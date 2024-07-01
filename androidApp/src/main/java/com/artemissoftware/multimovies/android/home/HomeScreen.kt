package com.artemissoftware.multimovies.android.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.multimovies.android.GreetingView
import com.artemissoftware.multimovies.android.MyApplicationTheme
import com.artemissoftware.multimovies.android.PreviewData
import com.artemissoftware.multimovies.android.home.composables.MovieItem
import com.artemissoftware.multimovies.android.theme.Red
import com.artemissoftware.multimovies.domain.models.Movie

@Composable
fun HomeScreen(
    state: HomeState,
    event: (HomeEvent) -> Unit,
    navigateToDetail: (Movie) -> Unit
) {
//    val pullRefreshState = rememberPullRefreshState(
//        refreshing = uiState.refreshing,
//        onRefresh = { loadNextMovies(true) }
//    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
//            .pullRefresh(state = pullRefreshState)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                state.movies,
                key = { _, movie -> movie.id }
            ) { index, movie ->
                MovieItem(
                    movie = movie,
                    onClick = { navigateToDetail(movie) }
                )

                if (state.isEndOfList(index)) {
                    LaunchedEffect(key1 = Unit) {
                        event(HomeEvent.LoadNextMovies /*(false)*/)
                    }
                }
            }

            if (state.loading && state.movies.isNotEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            color = Red
                        )
                    }
                }
            }
        }

//        PullRefreshIndicator(
//            refreshing = uiState.refreshing,
//            state = pullRefreshState,
//            modifier = modifier.align(Alignment.TopCenter)
//        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MyApplicationTheme {
        HomeScreen(
            state = PreviewData.homeState,
            event = {},
            navigateToDetail = {}
        )
    }
}