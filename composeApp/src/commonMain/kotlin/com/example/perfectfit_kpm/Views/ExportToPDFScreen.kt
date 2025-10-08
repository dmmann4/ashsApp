package com.example.perfectfit_kpm.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perfectfit_kpm.Exercise
import org.jetbrains.compose.resources.painterResource
import perfectfit_kpm.composeapp.generated.resources.Res
import perfectfit_kpm.composeapp.generated.resources.gastrocrelease
import perfectfit_kpm.composeapp.generated.resources.kettlebellgetup
import perfectfit_kpm.composeapp.generated.resources.obleiquestretch

@Composable
fun ExportToPDFScreen(
    exercises: List<Exercise>,
    innerPadding: PaddingValues,
    onBack: () -> Unit
) {
    println("I am being shown export pdf screen $exercises")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(exercises) { exercise ->
            val painter = when (exercise.name.lowercase()) {
                "pushup" -> painterResource(Res.drawable.gastrocrelease)
                "squat" -> painterResource(Res.drawable.kettlebellgetup)
                else -> painterResource(Res.drawable.obleiquestretch)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.1f))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = exercise.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                )

                Column {
                    Text(exercise.name, fontWeight = FontWeight.Bold)
                    Text(exercise.shortDescription, fontSize = 12.sp)
                }
            }
        }
    }
}