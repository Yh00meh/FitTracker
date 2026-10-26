package com.example.fittracker.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Top App Bar header component
 * Displays the app title and an optional back navigation button based on the navigation stack state.
 *
 * @param currentRoute The string route of the currently displayed screen.
 * @param canNavigateBack Determines if the back arrow button should be visible.
 * @param onNavigateBack Action to perform when the back arrow is clicked.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Header(
    currentRoute: String?,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "FitTracker")
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}



@Preview
@Composable
fun HeaderPreview() {
    Header("main", true, {})
}

