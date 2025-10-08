package com.example.perfectfit_kpm

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

//// Update to use the InputChips view type
@Composable
fun FilterButton(
    selectedFilter: Exerciseable?,
    onFilterSelected: (Exerciseable?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = "Filter"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            DropdownMenuItem(
                text = { Text("All") },
                onClick = {
                    onFilterSelected(null)
                    expanded = false
                },
                colors = MenuItemColors(
                    MaterialTheme.colorScheme.primary,
                    leadingIconColor = MaterialTheme.colorScheme.primary,
                    trailingIconColor = MaterialTheme.colorScheme.primary,
                    disabledTextColor = MaterialTheme.colorScheme.primary,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.primary,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.primary
                )
            )
            Equipment.values().forEach { type ->
                DropdownMenuItem(
                    text = { Text(type.stringValue) },
                    onClick = {
                        onFilterSelected(type)
                        expanded = false
                    },
                    colors = MenuItemColors(
                        MaterialTheme.colorScheme.primary,
                        leadingIconColor = MaterialTheme.colorScheme.primary,
                        trailingIconColor = MaterialTheme.colorScheme.primary,
                        disabledTextColor = MaterialTheme.colorScheme.primary,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.primary,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}