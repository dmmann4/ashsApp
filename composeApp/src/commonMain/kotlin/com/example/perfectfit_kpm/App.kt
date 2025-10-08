package com.example.perfectfit_kpm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import com.example.perfectfit_kpm.Models.NavigationManager
import kotlinx.coroutines.flow.map


sealed class Screen {
    object Home : Screen()
    data class Detail(val exercise: Exercise) : Screen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navManager = remember { NavigationManager() }
    val currentScreen by navManager.currentScreen.collectAsState()
    val canGoBack = navManager.currentScreen.map {
        navManager.canGoBack()
    }.collectAsState(initial = false)
    val selectedItems = remember { mutableStateListOf<Exercise>() }

    MaterialTheme(
        colorScheme = LightColors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes
    ) {
        BackAwareScaffold(
            canGoBack = canGoBack.value,
            onBack = { navManager.goBack() },
            selectedItems = selectedItems,
            actions = {
                if (currentScreen is Screen.Detail) {
                    IconButton(onClick = {
                        val exercise = (currentScreen as Screen.Detail).exercise
                        selectedItems.add(exercise)
                        navManager.goBack()
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Exercise")
                    }
                }
            }
        ) { innerPadding ->
            when (currentScreen) {
                is Screen.Home -> MainView(
                    selectedItems = selectedItems,
                    onItemClick = { exercise -> navManager.navigateTo(Screen.Detail(exercise))},
                    innerPadding = innerPadding
                )

                is Screen.Detail -> ExerciseDetailScreen(
                    initialItem = (currentScreen as Screen.Detail).exercise,
                    onAdd = { updatedItem ->
                        if (!selectedItems.contains(updatedItem)) {
                            selectedItems.add(updatedItem)
                        }
                        navManager.goBack()
                    }
                )
            }
        }
    }
}