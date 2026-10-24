package com.example.fittracker.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fittracker.component.ExercisePicker
import com.example.fittracker.component.Header
import com.example.fittracker.component.getCategorizedExercises

@Composable
fun ExerciseListScreen(navController: NavController, categoryName: String?) {
    val exercises = getCategorizedExercises()[categoryName] ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Header(
            currentRoute = "exerciseList",
            canNavigateBack = true,
            onNavigateBack = { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Exercises for ${categoryName ?: "Workout"}",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExercisePicker(exercises = exercises)
    }
}

@Preview
@Composable
fun ExerciseListScreenPreview() {
    ExerciseListScreen(navController = NavController(LocalContext.current), categoryName = "Cardio")
}
