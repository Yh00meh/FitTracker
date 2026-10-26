package com.example.fittracker.nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fittracker.MyViewModel
import com.example.fittracker.component.DetailLoggerCard
import com.example.fittracker.component.Header
import com.example.fittracker.component.getExerciseDetails


@Composable
fun ExerciseDetailScreen(
    navController: NavController,
    exerciseName: String?,
    viewModel: MyViewModel = viewModel()
) {
    val details = getExerciseDetails(exerciseName)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Header(
            currentRoute = "exerciseDetails",
            canNavigateBack = true,
            onNavigateBack = { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (details != null) {
            // Exercise Title
            Text(
                text = details.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Recommended Timing / Frequency
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Recommended Timing / Sets",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = details.recommendedTiming,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Workout Time Logger
            DetailLoggerCard(
                exerciseName = details.name,
                onLogWorkout = { minutes ->
                    viewModel.logWorkout(details.name, minutes)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Steps Title
            Text(
                text = "Instructions",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // List of Steps
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(details.steps) { index, step ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "${index + 1}.",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(end = 12.dp)
                            )
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        } else {
            Text(
                text = "Exercise details not found.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExerciseDetailsScreenPreview() {
    MaterialTheme {
        ExerciseDetailScreen(navController = NavController(LocalContext.current), exerciseName = "Deadlift")
    }
}