package com.example.perfectfit_kpm.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.perfectfit_kpm.Exercise
import com.example.perfectfit_kpm.Models.PdfExportResult
import com.example.perfectfit_kpm.Models.exportExercisesToPdf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    var exercises by mutableStateOf<List<Exercise>>(emptyList())
        private set

    var pdfState by mutableStateOf<PdfExportResult?>(null)
        private set

    init {

    }

    fun exportToPdf() {
        viewModelScope.launch {
            pdfState = exportExercisesToPdf(exercises, 595, 842) // A4 size in points
        }
    }

    fun onPdfShared() {
        pdfState = null
    }
}
