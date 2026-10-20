package com.example.fittracker.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fittracker.AppNavHost
import com.example.fittracker.component.CategoryPicker
import com.example.fittracker.component.SummaryStatsCard

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SummaryStatsCard()
        Spacer(modifier = Modifier.height(16.dp))
        CategoryPicker()

    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        AppNavHost()
    }
}