package com.example.perfectfit_kpm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseCollectionView(items: List<Exercise>, innerPadding: PaddingValues, selectedItems: MutableList<Exercise>, onItemClick: (Exercise) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier
            .fillMaxSize(),
//            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items) { item ->
            val isSelected = item in selectedItems
            ExerciseCard(exercise = item, isSelected = isSelected, onToggleClick = {
                println("Clicked: ${item.name}")
                if (isSelected) {
                    selectedItems.remove(item)
                } else {
                    selectedItems.add(item)
                }
            }, onImageClick = { onItemClick(item) })
        }
    }
}