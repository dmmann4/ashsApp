package com.example.perfectfit_kpm.ViewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.perfectfit_kpm.Exercise
import com.example.perfectfit_kpm.Models.Networking

class MainViewModel {

    val networking = Networking()
    var data by mutableStateOf<List<Exercise>?>(null)
        private set

    suspend fun loadData(): List<Exercise>? {
        val response = networking.getExercises()
        println("✅ Response body: $data")
        data = response
        return data
    }
}
