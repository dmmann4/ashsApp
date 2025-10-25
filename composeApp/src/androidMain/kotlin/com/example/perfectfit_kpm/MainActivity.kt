package com.example.perfectfit_kpm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import android.app.Activity

/**
 * A simple object to hold a static reference to the MainActivity.
 * This can be useful for accessing the activity context from other parts of the application,
 * but be cautious of memory leaks.
 */
object MainActivityHolder {
    var activity: Activity? = null
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        MainActivityHolder.activity = this

        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (MainActivityHolder.activity === this) MainActivityHolder.activity = null
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}