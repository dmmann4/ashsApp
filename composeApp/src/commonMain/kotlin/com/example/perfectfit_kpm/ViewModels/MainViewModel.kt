package com.example.perfectfit_kpm.ViewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.perfectfit_kpm.Exercise
import com.example.perfectfit_kpm.Models.Networking
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel {

    val networking = Networking()
    var data = MutableStateFlow<List<Exercise>?>(null)
        private set

    suspend fun loadData() {
        /// check for null here
        if (data.value != null) {
            val response = networking.getExercises()
            println("✅ Response body: $data")
            data.value = response
        }
    }
}
