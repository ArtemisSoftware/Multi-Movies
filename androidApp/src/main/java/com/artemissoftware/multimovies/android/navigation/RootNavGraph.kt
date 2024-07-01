package com.artemissoftware.multimovies.android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.multimovies.android.home.HomeScreen
import com.artemissoftware.multimovies.android.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootNavGraph(
    innerPaddings: PaddingValues,
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        modifier = Modifier.padding(innerPaddings),
        startDestination = startDestination
    ){
        composable(route = Route.Home.route){
            val homeViewModel: HomeViewModel = koinViewModel()
            HomeScreen(
                state = homeViewModel.state.collectAsState().value,
                event = homeViewModel::onTriggerEvent,
                navigateToDetail = {
//                    navController.navigate(
//                        "${Detail.route}/${it.id}"
//                    )
                }
            )
        }

//        composable(Detail.routeWithArgs, arguments = Detail.arguments){
//            val movieId = it.arguments?.getInt("movieId") ?: 0
//            val detailViewModel: DetailViewModel = koinViewModel(
//                parameters = { parametersOf(movieId) }
//            )
//
//            DetailScreen(uiState = detailViewModel.uiState)
//        }
    }
}

sealed class Route(
    title: String,
    route: String,
    routeWithArgs: String
): Destination(
    title = title,
    route = route,
    routeWithArgs = routeWithArgs
){
    data object Home: Route(title = "Movies", route = "home", routeWithArgs = "home")
}
