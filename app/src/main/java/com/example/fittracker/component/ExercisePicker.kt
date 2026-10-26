package com.example.fittracker.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fittracker.nav.getCategorizedExercises

// Data Model
data class Exercise(
    val name: String,
    val difficulty: String,
    val instructionPreview: String
)



@Composable
fun ExercisePicker(
    exercises: List<Exercise>,
    onExerciseClick: (Exercise) -> Unit = {}
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(exercises) { exercise ->
            ExerciseCard(exercise = exercise) {
                onExerciseClick(exercise)
            }
        }
    }
}
/**
 * Exercise Card component
 * Displays a card for each exercise
 *
 * @param exercise The exercise to display
 * @param onClick Called when the exercise card is clicked
 */
@Composable
fun ExerciseCard(exercise: Exercise, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        //Outputs the exercise details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            //Title
            Text(text = exercise.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            //Difficulty
            Text(text = "Difficulty: ${exercise.difficulty}", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(8.dp))
            //Instructions
            Text(text = exercise.instructionPreview, style = MaterialTheme.typography.bodyMedium)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ExercisePickerPreview() {
    MaterialTheme {
        val sampleExercises = getCategorizedExercises()["Cardio"] ?: emptyList()
        ExercisePicker(exercises = sampleExercises)
    }
}

