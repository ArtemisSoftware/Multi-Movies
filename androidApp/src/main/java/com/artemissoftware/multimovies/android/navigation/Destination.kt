package com.artemissoftware.multimovies.android.navigation

import androidx.navigation.NamedNavArgument

abstract class Destination(
    val title: String,
    private val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
){
    fun fullRoute(): String{
        return if (arguments.isEmpty()) route else completeRoute
    }

    private val completeRoute: String = buildString {
        append(route)
        arguments.forEachIndexed { index, custom ->
            val symbol = if (index == 0) "?" else "&"
            append("$symbol${custom.name}={${custom.name}}")
        }
    }

    fun withCustomArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEachIndexed { index, argument ->
                val json = argument
                val symbol = if (index == 0) "?" else "&"
                append("$symbol${arguments[index].name}=$json")
            }
        }
    }
}

val destinations = listOf(Route.Home)