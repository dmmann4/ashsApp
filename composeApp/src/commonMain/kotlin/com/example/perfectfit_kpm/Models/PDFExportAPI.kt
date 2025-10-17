package com.example.perfectfit_kpm.Models

import com.example.perfectfit_kpm.Exercise

expect class PdfExportResult(filename: String, bytes: ByteArray) {
    val filename: String
    val bytes: ByteArray
}

expect suspend fun exportExercisesToPdf(
    exercises: List<Exercise>,
    pageWidthPoints: Int,
    pageHeightPoints: Int
): PdfExportResult
