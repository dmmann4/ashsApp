package com.example.perfectfit_kpm.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.perfectfit_kpm.ViewModels.MainViewModel



@Composable
fun BodyPartFilterHorizontalScrollView(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(viewModel.currentBodyPartFilter.entries.toList()) { item ->
                Box(
                    contentAlignment = Alignment.Center,

                    modifier = Modifier
                        .background(
                            color = if (viewModel.getSelectedFilteredBodyPart(item.key)) Color.DarkGray else Color.LightGray,
                            shape = RoundedCornerShape(50) // Capsule shape
                        )
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .clickable {
                            viewModel.updateSelectedBodyPartFilters(item.key)
                            println("Box clicked!")
                        }
                ) {
                    Text(
                        text = item.key.stringValue,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
