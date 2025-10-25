package com.example.perfectfit_kpm.ViewModels
import androidx.compose.runtime.mutableStateMapOf
import com.example.perfectfit_kpm.Exercise
import com.example.perfectfit_kpm.ExerciseBodyPart
import com.example.perfectfit_kpm.Exerciseable
import com.example.perfectfit_kpm.Models.Networking
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel {

    val networking = Networking()
    var data = MutableStateFlow<List<Exercise>?>(null)
        private set
    val currentBodyPartFilter = mutableStateMapOf<Exerciseable, Boolean>(
        ExerciseBodyPart.all to true,
        ExerciseBodyPart.arms to false,
        ExerciseBodyPart.ankles to false,
        ExerciseBodyPart.feet to false,
        ExerciseBodyPart.glutes to false,
        ExerciseBodyPart.hips to false,
        ExerciseBodyPart.knees to false,
        ExerciseBodyPart.shoulders to false,
        ExerciseBodyPart.upperBack to false,
        ExerciseBodyPart.chest to false,
        ExerciseBodyPart.lowerBack to false,
        ExerciseBodyPart.neck to false,
        ExerciseBodyPart.core to false
    )
    suspend fun loadData() {
        /// check for null here
        println("✅ Response body pre null check: " + data.value)
        println()
        if (data.value == null) {
            val response = networking.getExercises()
            println("✅ Response body: $data")
            data.value = response
        }
        println("✅ Response body pre null check: " + currentBodyPartFilter)
        sortSelectedBodyPartFilters()
    }

    fun updateSelectedBodyPartFilters(filter: Exerciseable) {
        val currentValue = currentBodyPartFilter[filter] ?: false
        currentBodyPartFilter[filter] = !currentValue
        configureAllBodyPartFilter()
        sortSelectedBodyPartFilters()
    }

    fun getSelectedFilteredBodyPart(filter: Exerciseable): Boolean {
        return currentBodyPartFilter[filter] == true
    }

    private fun configureAllBodyPartFilter() {
        // If no filters are selected at all, turn '.all' back on.
        if (currentBodyPartFilter.values.none { it }) {
            currentBodyPartFilter[ExerciseBodyPart.all] = true
        }
    }

    private fun sortSelectedBodyPartFilters() {
        val comparator = compareBy<Map.Entry<Exerciseable, Boolean>> {
            // false is sorted before true, so if key is not .all, it will be sorted after .all
            it.key != ExerciseBodyPart.all
        }.thenByDescending {
            // true is sorted before false
            it.value
        }

        val sortedEntries = currentBodyPartFilter.entries.sortedWith(comparator)

        currentBodyPartFilter.clear()
        sortedEntries.forEach { (key, value) ->
            currentBodyPartFilter[key] = value
        }
    }
}
