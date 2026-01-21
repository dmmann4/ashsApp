package com.example.perfectfit_kpm.Views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Multiplatform media player view. Renders either an image or a video depending on the mediaUrl and is expected to be implemented per platform.
 * @param mediaUrl The url or resource identifier for the media
 * @param modifier Compose modifier
 */
@Composable
expect fun MediaPlayerView(
    mediaUrl: String,
    modifier: Modifier = Modifier
)
