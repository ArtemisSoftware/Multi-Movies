package com.artemissoftware.multimovies.android.navigation

abstract class Destination(
    val title: String,
    val route: String,
    val routeWithArgs: String
)

val destinations = listOf(Route.Home)