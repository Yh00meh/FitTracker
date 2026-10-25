package com.example.fittracker.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fittracker.MyViewModel

@Composable
fun AppNavHost(
    viewModel: MyViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, viewModel)
        }

        composable("exerciseList/{categoryName}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            ExerciseListScreen(navController, categoryName)
        }

        composable("exerciseDetails/{exerciseName}") { backStackEntry ->
            val exerciseName = backStackEntry.arguments?.getString("exerciseName")
            ExerciseDetailScreen(navController, exerciseName, viewModel)
        }
    }
}