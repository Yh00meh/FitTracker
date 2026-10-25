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
import com.example.fittracker.component.Exercise
import com.example.fittracker.component.ExercisePicker
import com.example.fittracker.component.Header

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

        ExercisePicker(
            exercises = exercises,
            onExerciseClick = { exercise ->
                navController.navigate("exerciseDetails/${exercise.name}")
            }
        )
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


@Preview
@Composable
fun ExerciseListScreenPreview() {
    ExerciseListScreen(navController = NavController(LocalContext.current), categoryName = "Cardio")
}
