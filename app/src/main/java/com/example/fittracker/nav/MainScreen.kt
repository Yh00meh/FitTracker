package com.example.fittracker.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fittracker.MyViewModel
import com.example.fittracker.component.CategoryPicker
import com.example.fittracker.component.Header
import com.example.fittracker.component.LogHistoryCard
import com.example.fittracker.component.SummaryStatsCard

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MyViewModel = viewModel()
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Header("main", false, {})
        Spacer(modifier = Modifier.height(16.dp))
        SummaryStatsCard(
            workoutCount = viewModel.totalWorkouts,
            totalMinutes = viewModel.totalMinutes
        )

        Spacer(modifier = Modifier.height(16.dp))
        CategoryPicker(
            onCategoryClick = { category ->
                navController.navigate("exerciseList/${category.name}")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        LogHistoryCard(
            logs = viewModel.workoutLogs,
            onDeleteLog = { item -> viewModel.deleteLog(item) },
            onClearAll = { viewModel.clearLogHistory() }
        )

    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        AppNavHost()
    }
}