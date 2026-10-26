package com.example.fittracker.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Data class to represent the detailed view of an exercise
data class ExerciseDetails(
    val name: String,
    val recommendedTiming: String,
    val steps: List<String>
)

/**
 * Detail Logger Card component
 * Form used in the ExerciseDeatailScreen to log workouts
 *
 * @param exerciseName The name of the exercise
 * @param onLogWorkout Called logWorkout from the view model
 */
@Composable
fun DetailLoggerCard(
    exerciseName: String = "Workout",
    onLogWorkout: (minutes: Int) -> Unit = {}
) {
    // State variables
    var timeInput by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var loggedMessage by remember { mutableStateOf<String?>(null) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            //Title
            Text(
                text = "Log $exerciseName Time",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Form to log time
            OutlinedTextField(
                value = timeInput,
                //Input validation
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        timeInput = newValue
                        isError = false
                        loggedMessage = null
                    }
                },
                label = { Text("Duration (minutes)") },
                suffix = { Text("min") },
                singleLine = true,
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            text = "Please enter a valid time in minutes",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                //Limits the users input to numbers only
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Button to log time
            Button(
                //Calls the onLogWorkout function from the view model
                onClick = {
                    val minutes = timeInput.toIntOrNull()
                    if (minutes != null && minutes > 0) {
                        onLogWorkout(minutes)
                        loggedMessage = "Successfully logged $minutes min!"
                        timeInput = ""
                        isError = false
                    } else {
                        isError = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Log Time")
            }

            loggedMessage?.let { msg ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = msg,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DetailLoggerCardPreview() {
    MaterialTheme {
        DetailLoggerCard(exerciseName = "Running")
    }
}