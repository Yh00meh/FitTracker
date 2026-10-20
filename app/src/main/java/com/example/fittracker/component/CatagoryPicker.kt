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
fun CategoryPicker() {

    //Sample data
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
            items(workoutCategories) { category ->
                WorkoutCategoryCard(category = category) {
                    println("Clicked on ${category.name}")
                }
            }
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun CategoryPickerCardPreview() {
    MaterialTheme {
        CategoryPicker()
    }
}

