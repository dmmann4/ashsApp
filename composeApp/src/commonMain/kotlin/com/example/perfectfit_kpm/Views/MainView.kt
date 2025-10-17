package com.example.perfectfit_kpm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    val viewModel = remember { MainViewModel() } // Use remember to keep the same ViewModel instance
    var query by remember { mutableStateOf("") }

    // 1. Collect the original, unmodified list from the ViewModel
    val allExercises by viewModel.data.collectAsState()

    // 2. Create a separate mutable state for the filtered list that will be displayed
    var filteredExercises by remember { mutableStateOf<List<Exercise>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    // 3. Use LaunchedEffect to react to changes in the original list or the query
    LaunchedEffect(allExercises, query) {
        filteredExercises = if (query.length >= 2) {
            allExercises?.filter { it.name.contains(query, ignoreCase = true) } ?: emptyList()
        } else {
            allExercises ?: emptyList()
        }
    }


    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        SearchBar(
            placeholder = "Search exercises…",
            onQueryChange = { search ->
                query = search
                // The LaunchedEffect above will handle the filtering automatically
                println("Searching for: $query")
            },
            modifier = Modifier.padding(5.dp),
            query = query
        )
        Box(modifier = Modifier.fillMaxSize()) { // Make Box fill the remaining space
            // 4. Use the new filteredExercises state here
            ExerciseCollectionView(
                items = filteredExercises,
                innerPadding = innerPadding,
                selectedItems = selectedItems,
                onItemClick = onItemClick)

            if (selectedItems.isNotEmpty()) {
                SelectedExercisesBar(
                    items = selectedItems,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
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
