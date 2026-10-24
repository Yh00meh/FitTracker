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

//Data Model
data class WorkoutCategory(val id: Int, val name: String, val description: String)

@Composable
fun CategoryPicker(onCategoryClick: (WorkoutCategory) -> Unit = {}) {

    val workoutCategories = listOf(
        WorkoutCategory(1, "Cardio", "N/A"),
        WorkoutCategory(2, "Strength", "N/A"),
        WorkoutCategory(3, "Flexibility", "N/A"),
        WorkoutCategory(4, "HIIT", "N/A"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Workout Categories",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            //Iterates through the workoutCategories list and outputs the card
            // inside the lazy Column
            items(workoutCategories) { category ->
                WorkoutCategoryCard(category = category) {
                    onCategoryClick(category)
                }
            }
        }
    }
}

//takes information fromt the data model and creates a card
@Composable
fun WorkoutCategoryCard(category: WorkoutCategory, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = category.name, style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = category.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun getCategorizedExercises(): Map<String, List<Exercise>> {
    return mapOf(
        "Cardio" to listOf(
            Exercise(
                name = "Running",
                difficulty = "Beginner",
                instructionPreview = "Maintain a steady, comfortable pace while landing mid-foot with an upright posture."
            ),
            Exercise(
                name = "Jump Rope",
                difficulty = "Intermediate",
                instructionPreview = "Keep your elbows close to your sides and use your wrists, not your entire arms, to swing the rope."
            ),
            Exercise(
                name = "Rowing",
                difficulty = "Intermediate",
                instructionPreview = "Drive powerfully with your legs first, lean back slightly, and then pull the handle to your lower chest."
            )
        ),
        "Strength" to listOf(
            Exercise(
                name = "Barbell Squat",
                difficulty = "Intermediate",
                instructionPreview = "Keep your chest up, push your hips back, and lower your body until your thighs are parallel to the floor."
            ),
            Exercise(
                name = "Push-Up",
                difficulty = "Beginner",
                instructionPreview = "Maintain a straight line from your head to your heels while lowering your chest close to the ground."
            ),
            Exercise(
                name = "Deadlift",
                difficulty = "Advanced",
                instructionPreview = "Keep the barbell close to your shins, engage your lats, and drive through your heels to stand tall."
            )
        ),
        "Flexibility" to listOf(
            Exercise(
                name = "Hamstring Stretch",
                difficulty = "Beginner",
                instructionPreview = "Hinge forward at your hips with a straight back and reach gently toward your toes until you feel a stretch."
            ),
            Exercise(
                name = "Child's Pose",
                difficulty = "Beginner",
                instructionPreview = "Kneel on the floor, sit back onto your heels, and reach your arms forward while lowering your chest."
            ),
            Exercise(
                name = "Pigeon Pose",
                difficulty = "Intermediate",
                instructionPreview = "Bring one knee forward behind your wrist and extend the back leg straight while folding your torso down."
            )
        ),
        "HIIT" to listOf(
            Exercise(
                name = "Burpees",
                difficulty = "Advanced",
                instructionPreview = "Drop into a plank, perform a push-up, jump your feet forward, and explode straight up into the air."
            ),
            Exercise(
                name = "Mountain Climbers",
                difficulty = "Intermediate",
                instructionPreview = "Start in a high plank and rapidly drive alternating knees toward your chest while keeping your core tight."
            ),
            Exercise(
                name = "Jump Squats",
                difficulty = "Intermediate",
                instructionPreview = "Lower into a deep squat and explode upward vertically, landing softly back down into the squat position."
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryPickerCardPreview() {
    MaterialTheme {
        CategoryPicker()
    }
}

