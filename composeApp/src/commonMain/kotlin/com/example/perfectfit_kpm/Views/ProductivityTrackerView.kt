package com.example.perfectfit_kpm.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

@Composable
fun ProductivityTrackerView(innerPadding: PaddingValues) {
    var dailyMinutes by remember { mutableStateOf(408) }
    var productivity by remember { mutableStateOf(90) } // percentage
    var lunchMinutes by remember { mutableStateOf(0) }

    val primary = MaterialTheme.colorScheme.primary
    val onSurface = MaterialTheme.colorScheme.onSurface
    val surface = MaterialTheme.colorScheme.surface
    val dividerColor = primary.copy(alpha = 0.6f)

    Surface(tonalElevation = 0.dp, modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Daily Minutes", style = MaterialTheme.typography.titleSmall, color = onSurface)
                Text("Productivity", style = MaterialTheme.typography.titleSmall, color = onSurface)
            }

            Spacer(Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                // Daily minutes stepper
                StepperBox(
                    valueText = dailyMinutes.toString(),
                    suffix = "",
                    onIncrement = { dailyMinutes += 1 },
                    onDecrement = { if (dailyMinutes > 0) dailyMinutes -= 1 },
                    primary = primary,
                    surface = surface,
                    onSurface = onSurface,
                )
                // Productivity percent
                MetricBox(
                    valueText = "$productivity %",
                    primary = primary,
                    surface = Color(0xFF1C1C1C).copy(alpha = 0.8f),
                    onSurface = onSurface,
                    modifier = Modifier.weight(1f).padding(start = 16.dp)
                )
            }

            Spacer(Modifier.height(8.dp))
            ArrowIndicator(up = false, color = onSurface)

            Spacer(Modifier.height(8.dp))
            DividerLine(color = dividerColor)

            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Clock In", style = MaterialTheme.typography.titleSmall, color = onSurface)
                Text("Target Out", style = MaterialTheme.typography.titleSmall, color = onSurface)
            }

            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("6:08 AM", fontSize = 32.sp, color = onSurface)
                Text("1:41 PM", fontSize = 32.sp, color = onSurface)
            }

            Spacer(Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("10/19/24", style = MaterialTheme.typography.labelMedium, color = onSurface.copy(alpha = 0.75f))
                Text("10/19/24", style = MaterialTheme.typography.labelMedium, color = onSurface.copy(alpha = 0.75f))
            }

            Spacer(Modifier.height(16.dp))
            DividerLine(color = dividerColor)

            Spacer(Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Start Lunch", style = MaterialTheme.typography.titleSmall, color = onSurface)
                Text("Time For Lunch", style = MaterialTheme.typography.titleSmall, color = onSurface)
            }

            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(surface)
                        .border(1.dp, primary, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🍽", fontSize = 24.sp)
                }

                Row(modifier = Modifier.weight(1f).padding(start = 16.dp), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                    MetricBox(
                        valueText = "$lunchMinutes min",
                        primary = primary,
                        surface = Color(0xFF1C1C1C).copy(alpha = 0.8f),
                        onSurface = onSurface,
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
            ArrowIndicator(up = false, color = onSurface)

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { /* clock out action hook */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE67E44),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(22.dp)
            ) {
                Text("Clock Out", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@Composable
private fun DividerLine(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color)
    )
}

@Composable
private fun ArrowIndicator(up: Boolean, color: Color) {
    Text(
        text = if (up) "▲" else "▼",
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun MetricBox(
    valueText: String,
    primary: Color,
    surface: Color,
    onSurface: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(surface)
            .border(1.dp, primary, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            valueText,
            color = onSurface,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
private fun StepperBox(
    valueText: String,
    suffix: String,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    primary: Color,
    surface: Color,
    onSurface: Color,
) {
    val shape = RoundedCornerShape(14.dp)
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .background(surface)
                .border(1.dp, primary, shape)
                .padding(horizontal = 18.dp, vertical = 10.dp)
        ) {
            Text(text = valueText + suffix, style = MaterialTheme.typography.titleLarge, color = onSurface)
        }
        Spacer(Modifier.size(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            SmallPill("+", primary, onSurface, onIncrement)
            SmallPill("-", primary, onSurface, onDecrement)
        }
    }
}

@Composable
private fun SmallPill(label: String, primary: Color, onSurface: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(primary.copy(alpha = 0.15f))
            .border(1.dp, primary, RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(label, color = onSurface, fontWeight = FontWeight.SemiBold)
    }
}