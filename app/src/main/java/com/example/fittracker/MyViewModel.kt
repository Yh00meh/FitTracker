package com.example.fittracker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID

// Data model for workout logs
data class WorkoutLogItem(
    val id: String = UUID.randomUUID().toString(), // Assigns a immutable Unique ID for each log entry
    val exerciseName: String,
    val minutes: Int
)

class MyViewModel : ViewModel() {
    var totalWorkouts by mutableIntStateOf(0)
        private set

    var totalMinutes by mutableIntStateOf(0)
        private set

    // List of workout logs
    val workoutLogs = mutableStateListOf<WorkoutLogItem>()

    //Logs workout and updates stats
    fun logWorkout(exerciseName: String, minutes: Int) {
        if (minutes > 0) {
            val newItem = WorkoutLogItem(exerciseName = exerciseName, minutes = minutes)
            workoutLogs.add(0, newItem)
            totalWorkouts += 1
            totalMinutes += minutes
        }
    }

    //Deletes log entry and updates stats
    fun deleteLog(item: WorkoutLogItem) {
        if (workoutLogs.remove(item)) {
            totalWorkouts = (totalWorkouts - 1).coerceAtLeast(0)
            totalMinutes = (totalMinutes - item.minutes).coerceAtLeast(0)
        }
    }

    //Clears all log entries and resets stats
    fun clearLogHistory() {
        workoutLogs.clear()
        totalWorkouts = 0
        totalMinutes = 0
    }
}