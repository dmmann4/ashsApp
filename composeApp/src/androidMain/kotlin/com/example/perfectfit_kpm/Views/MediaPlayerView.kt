package com.example.perfectfit_kpm.Views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.VideoView
import android.net.Uri
import android.widget.MediaController
import android.widget.ImageView
import android.graphics.Color
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import androidx.core.net.toUri

@Composable
actual fun MediaPlayerView(
    mediaUrl: String,
    modifier: Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            // Decide whether it's an image or video based on file extension
            val isVideo = mediaUrl.endsWith(".mp4") || mediaUrl.endsWith(".mov") || mediaUrl.endsWith(".avi")
            if (isVideo) {
                val videoView = VideoView(context)
                videoView.setVideoURI(mediaUrl.toUri())
                videoView.setMediaController(MediaController(context))
                videoView.setBackgroundColor(Color.BLACK)
                videoView.setOnPreparedListener { it.isLooping = true; videoView.start() }
                videoView
            } else {
                val imageView = ImageView(context)
                Glide.with(context).load(mediaUrl).into(imageView)
                imageView.setBackgroundColor(Color.BLACK)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView
            }
        }
    )
}
