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

@Composable
fun DetailLoggerCard(
    exerciseName: String = "Workout",
    onLogWorkout: (minutes: Int) -> Unit = {}
) {
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
            Text(
                text = "Log $exerciseName Time",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = timeInput,
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
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

// Function to search and retrieve detailed step-by-step instructions
fun getExerciseDetails(exerciseName: String?): ExerciseDetails? {
    val detailsDatabase = mapOf(
        // Cardio Exercises
        "running" to ExerciseDetails(
            name = "Running",
            recommendedTiming = "20–30 minutes, 3–4 times per week (Moderate intensity)",
            steps = listOf(
                "Warm up with a 5-minute brisk walk to prepare your muscles.",
                "Maintain an upright torso, keeping your shoulders relaxed and elbows bent at 90 degrees.",
                "Land softly on your mid-foot under your hips rather than striking hard on your heel.",
                "Cool down with a 5-minute slow walk and light stretching."
            )
        ),
        "jump rope" to ExerciseDetails(
            name = "Jump Rope",
            recommendedTiming = "10–15 minutes (or 1-minute intervals with 30-second breaks)",
            steps = listOf(
                "Hold the handles with a light grip and keep your elbows tucked close to your body.",
                "Flick your wrists to rotate the rope rather than swinging your entire arms.",
                "Jump on the balls of your feet, staying just 1–2 inches off the ground.",
                "Land softly on knees slightly bent to absorb the impact."
            )
        ),
        "rowing" to ExerciseDetails(
            name = "Rowing",
            recommendedTiming = "15–20 minutes at a steady pace or 500m intervals",
            steps = listOf(
                "Sit on the rower with feet strapped in and grab the handle with an overhand grip.",
                "Drive back with your legs first until they are nearly straight.",
                "Lean slightly back at the hips, then pull the handle smoothly toward your lower chest.",
                "Reverse the motion: extend arms, hinge forward at hips, then bend knees back to start."
            )
        ),

        // Strength Exercises
        "barbell squat" to ExerciseDetails(
            name = "Barbell Squat",
            recommendedTiming = "3–4 sets of 8–12 reps (60–90 seconds rest between sets)",
            steps = listOf(
                "Position the barbell across your upper back/traps and unrack it safely.",
                "Stand with feet shoulder-width apart, toes turned slightly outward.",
                "Inhale, brace your core, push your hips back, and lower your hips until parallel to the knees.",
                "Exhale and drive through your heels to return to standing position."
            )
        ),
        "push-up" to ExerciseDetails(
            name = "Push-Up",
            recommendedTiming = "3 sets of 10–20 reps (or to near failure)",
            steps = listOf(
                "Place hands slightly wider than shoulder-width apart on the floor in a plank position.",
                "Keep your head, spine, and hips in a rigid, straight line by engaging your core and glutes.",
                "Lower your chest down until your elbows form a 45-degree angle to your torso.",
                "Push firmly through your palms to press back up to full extension."
            )
        ),
        "deadlift" to ExerciseDetails(
            name = "Deadlift",
            recommendedTiming = "3–5 sets of 3–6 reps (2–3 minutes rest between heavy sets)",
            steps = listOf(
                "Stand with feet hip-width apart, barbell resting over your mid-foot.",
                "Hinge at the hips and grab the bar with a shoulder-width grip, keeping your back flat.",
                "Engage your lats, pull the slack out of the bar, and drive through your feet to stand upright.",
                "Hinge at your hips to lower the bar back to the floor under control."
            )
        ),

        // Flexibility Exercises
        "hamstring stretch" to ExerciseDetails(
            name = "Hamstring Stretch",
            recommendedTiming = "Hold for 30–60 seconds per leg, repeat 2–3 times",
            steps = listOf(
                "Sit on the floor with one leg straight and the other bent inward.",
                "Keep your back long and spine straight as you hinge forward at the hips.",
                "Reach gently toward your extended foot until you feel a comfortable stretch.",
                "Breathe deeply into the stretch without bouncing or forcing the range of motion."
            )
        ),
        "child's pose" to ExerciseDetails(
            name = "Child's Pose",
            recommendedTiming = "Hold for 1–2 minutes, breathing deeply throughout",
            steps = listOf(
                "Kneel on the floor with big toes touching and knees wide apart.",
                "Sit back toward your heels and extend your arms forward on the floor.",
                "Lower your forehead down to the mat and relax your shoulders.",
                "Take deep, long breaths into your belly and lower back."
            )
        ),
        "pigeon pose" to ExerciseDetails(
            name = "Pigeon Pose",
            recommendedTiming = "Hold for 45–60 seconds per side",
            steps = listOf(
                "Start in a high plank or downward dog position.",
                "Bring your right knee forward behind your right wrist, setting your shin angled on the mat.",
                "Slide your left leg straight back, squaring your hips toward the front.",
                "Lower down onto your forearms or extend arms straight ahead to deepen the hip stretch."
            )
        ),

        // HIIT Exercises
        "burpees" to ExerciseDetails(
            name = "Burpees",
            recommendedTiming = "4–5 rounds of 30 seconds work / 15 seconds rest",
            steps = listOf(
                "Stand tall, then drop quickly into a squat and place your hands on the floor.",
                "Kick your feet back into a high plank and lower your chest down in a push-up.",
                "Press up, jump your feet back under your hips, and explode vertically into the air.",
                "Land softly on bent knees and immediately transition into the next repetition."
            )
        ),
        "mountain climbers" to ExerciseDetails(
            name = "Mountain Climbers",
            recommendedTiming = "30–45 seconds per interval (HIIT block)",
            steps = listOf(
                "Start in a solid push-up plank position with hands under your shoulders.",
                "Drive your right knee up toward your chest while keeping your hips low.",
                "Quickly switch legs in a running motion, pulling the left knee in as the right goes back.",
                "Maintain a fast, rhythmic pace while keeping your shoulders stable."
            )
        ),
        "jump squats" to ExerciseDetails(
            name = "Jump Squats",
            recommendedTiming = "4 sets of 20 seconds work / 10 seconds rest (Tabata protocol)",
            steps = listOf(
                "Stand with feet shoulder-width apart and lower into a standard squat.",
                "Drive explosively off the ground, extending your ankles, knees, and hips.",
                "Reach your arms overhead as you jump into the air.",
                "Land softly on the balls of your feet, immediately absorbing the shock into the next squat."
            )
        )
    )

    // Lookup using lowercase string matching
    return detailsDatabase[exerciseName?.lowercase()?.trim() ?: ""]
}

@Preview(showBackground = true)
@Composable
fun DetailLoggerCardPreview() {
    MaterialTheme {
        DetailLoggerCard(exerciseName = "Running")
    }
}