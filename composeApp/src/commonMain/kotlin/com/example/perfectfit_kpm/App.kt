package com.example.perfectfit_kpm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.perfectfit_kpm.Models.NavigationManager
import com.example.perfectfit_kpm.Views.ExportToPDFScreen
import com.example.perfectfit_kpm.Views.ProductivityTrackerView
import com.example.perfectfit_kpm.Views.SettingsView
import kotlinx.coroutines.flow.map

sealed class Screen {
    object Home : Screen()
    data class Detail(val exercise: Exercise) : Screen()
    data class ExportReview(val exercises: List<Exercise>) : Screen()
}

private enum class BottomTab(val label: String) {
    Time("Time"),
    Program("Program"),
    Settings("Settings")
}


/// state rememberable set in nav host when navigating 

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navManager = remember { NavigationManager() }
    val currentScreen by navManager.currentScreen.collectAsState()
    val canGoBack = navManager.currentScreen.map {
        navManager.canGoBack()
    }.collectAsState(initial = false)
    val selectedItems = remember { mutableStateListOf<Exercise>() }
    var selectedTab by remember { mutableStateOf(BottomTab.Program) }
    val showBottomBar by remember(selectedTab, currentScreen) {
        mutableStateOf(
            when (selectedTab) {
                BottomTab.Program -> currentScreen is Screen.Home
                BottomTab.Time -> true
                BottomTab.Settings -> true
            }
        )
    }

    MaterialTheme(
        colorScheme = LightColors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes
    ) {
        BackAwareScaffold(
            canGoBack = if (selectedTab == BottomTab.Program) canGoBack.value else false,
            onBack = { if (selectedTab == BottomTab.Program) navManager.goBack() },
            selectedItems = selectedItems,
            actions = {
                if (selectedTab == BottomTab.Program && currentScreen is Screen.Detail) {
                    IconButton(onClick = {
                        val exercise = (currentScreen as Screen.Detail).exercise
                        selectedItems.add(exercise)
                        navManager.goBack()
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Exercise")
                    }
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
                    NavigationBarItem(
                        selected = selectedTab == BottomTab.Time,
                        onClick = { selectedTab = BottomTab.Time },
                        icon = { Icon(Icons.Default.AccessTime, contentDescription = "Time") },
                        label = { Text(BottomTab.Time.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                    NavigationBarItem(
                        selected = selectedTab == BottomTab.Program,
                        onClick = { selectedTab = BottomTab.Program },
                        icon = { Icon(Icons.Default.DirectionsRun, contentDescription = "Program") },
                        label = { Text(BottomTab.Program.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                    NavigationBarItem(
                        selected = selectedTab == BottomTab.Settings,
                        onClick = { selectedTab = BottomTab.Settings },
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                        label = { Text(BottomTab.Settings.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                    }
                }
            }
        ) { innerPadding ->
            when (selectedTab) {
                BottomTab.Program -> {
                    when (currentScreen) {
                        is Screen.Home -> MainView(
                            selectedItems = selectedItems,
                            onItemClick = { exercise -> navManager.navigateTo(Screen.Detail(exercise))},
                            onExportClick = { exercises ->
                                println("I am being told to navigate $exercises")
                                navManager.navigateTo(Screen.ExportReview(exercises))
                                println("current screen ${navManager.currentScreen}")
                            },
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
                        is Screen.ExportReview -> ExportToPDFScreen(
                            exercises = (currentScreen as Screen.ExportReview).exercises,
                            innerPadding = innerPadding,
                            onBack = { navManager.goBack() }
                        )
                    }
                }
                BottomTab.Time -> {
                    ProductivityTrackerView(innerPadding)
                }
                BottomTab.Settings -> {
                    SettingsView(innerPadding)
                }
            }
        }
    }
}