package com.example.fittracker.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//Data Model
data class WorkoutCategory(val id: Int, val name: String, val description: String)

/**
 * CatagoryPicker component
 * Selection of the big 4 workout categories
 *
 * @param onCategoryClick Called when a category is clicked
 */
@Composable
fun CategoryPicker(onCategoryClick: (WorkoutCategory) -> Unit = {}) {

    //List of workout categories
    val workoutCategories = listOf(
        WorkoutCategory(1, "Cardio", "Improve endurance, burn calories, and boost heart health"),
        WorkoutCategory(2, "Strength", "Build muscle mass, increase strength, and enhance power"),
        WorkoutCategory(3, "Flexibility", "Enhance mobility, stretch muscles, and improve posture"),
        WorkoutCategory(4, "HIIT", "High-intensity interval training for fast fat burn and stamina")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Title
        Text(
            text = "Workout Categories",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            workoutCategories.forEach { category ->
                WorkoutCategoryCard(category = category) {
                    onCategoryClick(category)
                }
            }
        }
    }
}

//This helper function takes information from the data model and creates a card
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
            //Name and Description
            Text(text = category.name, style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = category.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryPickerCardPreview() {
    MaterialTheme {
        CategoryPicker()
    }
}

