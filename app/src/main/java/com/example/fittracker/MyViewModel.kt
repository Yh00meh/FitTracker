package com.example.fittracker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var totalWorkouts by mutableIntStateOf(0)
        private set

    var totalMinutes by mutableIntStateOf(0)
        private set

    fun logWorkout(minutes: Int) {
        if (minutes > 0) {
            totalWorkouts += 1
            totalMinutes += minutes
        }
    }
}