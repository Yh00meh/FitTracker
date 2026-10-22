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
import com.example.fittracker.component.Header

data class Exercise(
    val name: String,
    val difficulty: String,
    val instructionPreview: String
)

@Composable
fun ExerciseListScreen(navController: NavController, categoryName: String?) {

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

    }
}


@Preview
@Composable
fun ExerciseListScreenPreview() {
    ExerciseListScreen(navController = NavController(LocalContext.current), categoryName = "Workout")
}
