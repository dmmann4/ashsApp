package com.example.perfectfit_kpm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perfectfit_kpm.Views.MediaPlayerView
import org.jetbrains.compose.resources.painterResource
import perfectfit_kpm.composeapp.generated.resources.Res
import perfectfit_kpm.composeapp.generated.resources.gastrocrelease
import perfectfit_kpm.composeapp.generated.resources.kettlebellgetup
import perfectfit_kpm.composeapp.generated.resources.obleiquestretch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    initialItem: Exercise,
    onAdd: (Exercise) -> Unit
) {
    var name by remember { mutableStateOf(initialItem.name) }
    var repetitions by remember { mutableStateOf(initialItem.sets) }
    var longDescription by remember { mutableStateOf(initialItem.longDescription) }
    var shortDesc by remember { mutableStateOf(initialItem.shortDescription) }
    var equipment by remember { mutableStateOf(initialItem.equipmentRequired) }
    var sets by remember { mutableStateOf(initialItem.sets) }
    var holdLength by remember { mutableStateOf(initialItem.holdLength) }
    var media = listOf(initialItem.imageRes)

    val painter = when (initialItem.name.lowercase()) {
        "pushup" -> painterResource(Res.drawable.gastrocrelease)
        "squat" -> painterResource(Res.drawable.kettlebellgetup)
        else -> painterResource(Res.drawable.obleiquestretch)
    }
    fun parseExerciseTypes(input: String): List<Equipment> {
        return input.split(",")
            .map { it.trim() }
            .mapNotNull { name -> Equipment.values().find { it.stringValue.equals(name, ignoreCase = true) } }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Exercise", color = MaterialTheme.colorScheme.primary) },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.background,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        val updatedItem = initialItem.copy(
                            name = name,
                            sets = sets,
                            longDescription = longDescription,
                            shortDescription = shortDesc,
                            equipmentRequired = equipment,
                            holdLength = holdLength
                        )
                        onAdd(updatedItem)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
//                Image(
//                    painter = painter,
//                    contentDescription = name,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(120.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    items(media.size) { url ->
                        MediaPlayerView(
                            mediaUrl = media[url],
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
            }
            item {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            item {
                OutlinedTextField(value = name, colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.primary, unfocusedTextColor = MaterialTheme.colorScheme.primary), onValueChange = { name = it }, label = { Text("Name") })
            }
            item {
                OutlinedTextField(value = repetitions, colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.primary, unfocusedTextColor = MaterialTheme.colorScheme.primary), onValueChange = { repetitions = it }, label = { Text("Repetitions") })
            }
            item {
                OutlinedTextField(value = longDescription, colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.primary, unfocusedTextColor = MaterialTheme.colorScheme.primary), onValueChange = { longDescription = it }, label = { Text("Description") })
            }
            item {
                OutlinedTextField(value = shortDesc, colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.primary, unfocusedTextColor = MaterialTheme.colorScheme.primary), onValueChange = { shortDesc = it }, label = { Text("Alt Description") })
            }
            item {
                OutlinedTextField(value = equipment, colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.primary, unfocusedTextColor = MaterialTheme.colorScheme.primary), onValueChange = { equipment = it }, label = { Text("Equipment") })
            }
            item {
                OutlinedTextField(value = sets, colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.primary, unfocusedTextColor = MaterialTheme.colorScheme.primary), onValueChange = { sets = it }, label = { Text("Sets") })
            }
            item {
                OutlinedTextField(value = holdLength, colors = OutlinedTextFieldDefaults.colors(MaterialTheme.colorScheme.primary, unfocusedTextColor = MaterialTheme.colorScheme.primary), onValueChange = { holdLength = it }, label = { Text("Hold Length") })
            }
            // optional extra padding at bottom
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}