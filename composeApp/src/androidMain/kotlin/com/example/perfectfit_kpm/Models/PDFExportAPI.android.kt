package com.example.perfectfit_kpm.Models

import android.graphics.pdf.PdfDocument
import android.graphics.Paint
import com.example.perfectfit_kpm.Exercise
import java.io.ByteArrayOutputStream

actual class PdfExportResult actual constructor(actual val filename: String, actual val bytes: ByteArray)

actual suspend fun exportExercisesToPdf(
    exercises: List<Exercise>,
    pageWidthPoints: Int,
    pageHeightPoints: Int
): PdfExportResult {
    val document = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(pageWidthPoints, pageHeightPoints, 1).create()
    val page = document.startPage(pageInfo)
    val canvas = page.canvas
    val paint = Paint()

    var yPosition = 72f
    for (exercise in exercises) {
        canvas.drawText(exercise.name, 72f, yPosition, paint)
        yPosition += 20
        canvas.drawText(exercise.shortDescription, 72f, yPosition, paint)
        yPosition += 40 // Add extra space between exercises
    }

    document.finishPage(page)

    val outputStream = ByteArrayOutputStream()
    document.writeTo(outputStream)
    document.close()

    val bytes = outputStream.toByteArray()
    return PdfExportResult("exercises.pdf", bytes)
}
