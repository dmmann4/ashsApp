package com.example.perfectfit_kpm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun BackAwareScaffold(
    canGoBack: Boolean,
    onBack: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    selectedItems: MutableList<Exercise>,
    content: @Composable (innerPadding: PaddingValues) -> Unit
) {
    var selectedFilter by remember { mutableStateOf<Exerciseable?>(null) }

    Scaffold(
        topBar = { TopAppBar(
            title = {
                Text("Ash's App",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
            },
            navigationIcon = {
                if (canGoBack) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                } else null
            },
            actions = actions,
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                scrolledContainerColor = MaterialTheme.colorScheme.background,
                navigationIconContentColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.background,
                actionIconContentColor = MaterialTheme.colorScheme.primary
            ),
        )
        },
        bottomBar = {

        },
        containerColor = MaterialTheme.colorScheme.background,
        content = { innerPadding ->
            content(innerPadding)
        }
    )
}