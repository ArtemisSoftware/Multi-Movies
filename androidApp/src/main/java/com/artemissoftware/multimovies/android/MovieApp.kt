package com.artemissoftware.multimovies.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.multimovies.android.composables.AppBar
import com.artemissoftware.multimovies.android.navigation.RootNavGraph
import com.artemissoftware.multimovies.android.navigation.Route
import com.artemissoftware.multimovies.android.navigation.destinations
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark){
        MaterialTheme.colorScheme.primaryContainer
    }else {
        Color.Transparent
    }
    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = destinations.find {
        backStackEntry?.destination?.route == it.fullRoute()
    }?: Route.Home

    Scaffold(
        topBar = {
            AppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                title = currentScreen.title,
                modifier = Modifier.fillMaxWidth(),
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    ) {innerPaddings ->
        
        RootNavGraph(
            innerPaddings = innerPaddings,
            navController = navController,
            startDestination = Route.Home.fullRoute()
        )
    }
}