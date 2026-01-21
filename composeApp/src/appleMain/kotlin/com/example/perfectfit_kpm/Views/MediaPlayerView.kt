package com.example.perfectfit_kpm.Views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.AVKit.AVPlayerViewController
import platform.AVFoundation.AVPlayer
import platform.Foundation.NSURL
import platform.Foundation.NSData
import platform.UIKit.UIImageView
import platform.UIKit.UIImage
import platform.UIKit.UIView
import platform.UIKit.UIViewContentMode
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MediaPlayerView(
    mediaUrl: String,
    modifier: Modifier
) {
    UIKitView(
        modifier = modifier,
        factory = {
            val isVideo = mediaUrl.endsWith(".mp4") || mediaUrl.endsWith(".mov") || mediaUrl.endsWith(".avi")
            if (isVideo) {
                val player = AVPlayer(URL = NSURL(string = mediaUrl))
                val controller = AVPlayerViewController()
                controller.player = player
                controller.showsPlaybackControls = true
                player.play()
                controller.view as UIView
            } else {
                val imageView = UIImageView()
                val url = NSURL(string = mediaUrl)
                val data = url?.let { NSData.dataWithContentsOfURL(it) }
                if (data != null) {
                    imageView.image = UIImage(data = data)
                }
                imageView.contentMode = UIViewContentMode.UIViewContentModeScaleAspectFill
                imageView
            }
        }
    )
}
