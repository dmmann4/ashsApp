package com.example.perfectfit_kpm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import perfectfit_kpm.composeapp.generated.resources.Res
import perfectfit_kpm.composeapp.generated.resources.gastrocrelease
import perfectfit_kpm.composeapp.generated.resources.kettlebellgetup
import perfectfit_kpm.composeapp.generated.resources.obleiquestretch


@Composable
fun ExerciseCard(
    exercise: Exercise,
    isSelected: Boolean,
    onToggleClick: () -> Unit,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(16.dp)
    val painter = when (exercise.name.lowercase()) {
        "pushup" -> painterResource(Res.drawable.gastrocrelease)
        "squat" -> painterResource(Res.drawable.kettlebellgetup)
        else -> painterResource(Res.drawable.obleiquestretch)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f)
            .clip(shape) // Apply rounded corners
            .border(
                BorderStroke(1.dp, Color.LightGray), // Visible, subtle border
                shape = shape
            )
    ) {
        // Background Image
        Image(
            painter = painter,
            contentDescription = exercise.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .clickable { onImageClick() }
        )

        // Add button (top-right corner)
        IconButton(
            onClick = onToggleClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = if (isSelected) Icons.Default.Close else Icons.Default.Add,
                contentDescription = if (isSelected) "Remove" else "Add",
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
                ,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = exercise.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(5.dp)
                .clip(shape)
                .background(Color.DarkGray)
                .padding(8.dp)
        )
    }
}