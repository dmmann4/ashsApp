package com.example.perfectfit_kpm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import perfectfit_kpm.composeapp.generated.resources.Res
import perfectfit_kpm.composeapp.generated.resources.gastrocrelease
import perfectfit_kpm.composeapp.generated.resources.kettlebellgetup
import perfectfit_kpm.composeapp.generated.resources.obleiquestretch

@Composable
fun SelectedExercisesBar(
    items: List<Exercise>,
    onRemoveClick: (Exercise) -> Unit
) {

    Surface(
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                Text("Exercise Count: ${items.size}", color = MaterialTheme.colorScheme.primary)
            }
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(items) { item ->
                        val painter = when (item.name.lowercase()) {
                            "pushup" -> painterResource(Res.drawable.gastrocrelease)
                            "squat" -> painterResource(Res.drawable.kettlebellgetup)
                            else -> painterResource(Res.drawable.obleiquestretch)
                        }
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = item.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                            )

                            IconButton(
                                onClick = { onRemoveClick(item) },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .background(Color.White, shape = CircleShape)
                                    .border(1.dp, Color.Gray, CircleShape)
                                    .size(20.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Remove",
                                    tint = Color.Black,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}