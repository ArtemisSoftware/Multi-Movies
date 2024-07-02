package com.artemissoftware.multimovies.android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artemissoftware.multimovies.android.detail.DetailScreen
import com.artemissoftware.multimovies.android.home.HomeScreen

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
        composable(route = Route.Home.fullRoute()){
            HomeScreen(
                navigateToDetail = {
                    navController.navigate(
                        Route.Detail.withCustomArgs(it.id)
                    )
                }
            )
        }

        composable(
            route = Route.Detail.fullRoute(),
            arguments = Route.Detail.arguments
        ){
            DetailScreen()
        }
    }
}

sealed class Route(
    title: String,
    route: String,
    arguments: List<NamedNavArgument> = emptyList()
): Destination(
    title = title,
    route = route,
    arguments = arguments
){
    data object Home: Route(title = "Movies", route = "home")

    data object Detail: Route(
        title = "Movies details",
        route = "detail",
        arguments = listOf(
            navArgument(name = NavArguments.MOVIE_ID){ type = NavType.IntType }
        )
    )
}
