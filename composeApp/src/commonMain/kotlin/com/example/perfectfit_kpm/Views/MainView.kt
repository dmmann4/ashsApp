package com.example.perfectfit_kpm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.perfectfit_kpm.ViewModels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    selectedItems: MutableList<Exercise>,
    onItemClick: (Exercise) -> Unit,
    onExportClick: (List<Exercise>) -> Unit,
    innerPadding: PaddingValues
) {
    val viewModel = MainViewModel()
    var query by remember { mutableStateOf("") }
    var exercises by remember { mutableStateOf<List<Exercise>?>(viewModel.data) }
    var selectedFilter by remember { mutableStateOf<Exerciseable?>(null) }
    LaunchedEffect(Unit) {
        if (exercises == null) {
            /// i need help persisting this when I go back to this view
            exercises = viewModel.loadData()
        }
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {

        Box() {
            SearchBar(
                placeholder = "Search exercises…",
                onQueryChange = { search ->
                    query = search
                    exercises = if (query.length >= 2) {
                        exercises
                            ?.filter { it.name.contains(query, ignoreCase = true) }
//                            ?.filter { selectedFilter == null || it.equipmentRequired.any { equipment -> equipment == selectedFilter } }
                    } else {
                        exercises
                    }
                    println("Searching for: $query")
                },
                modifier = Modifier.padding(5.dp),
                query = query
            )
            exercises?.let {
                ExerciseCollectionView(
                    items = it,
                    innerPadding = innerPadding,
                    selectedItems = selectedItems,
                    onItemClick = onItemClick)
            }
            if (selectedItems.isNotEmpty()) {
                SelectedExercisesBar(
                    items = selectedItems,
                    onExportClick = { exercises ->
                        println("I am passed properly to mainview")
                        onExportClick(exercises)
                    },
                    onRemoveClick = { selectedItems.remove(it) }
                )
            }
        }
    }
}