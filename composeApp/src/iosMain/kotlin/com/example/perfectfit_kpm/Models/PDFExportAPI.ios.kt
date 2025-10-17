package com.example.perfectfit_kpm.Models

import com.example.perfectfit_kpm.Exercise
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import platform.Foundation.*
import platform.UIKit.*
import platform.CoreGraphics.*
import platform.posix.memcpy

actual class PdfExportResult actual constructor(actual val filename: String, actual val bytes: ByteArray)

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
actual suspend fun exportExercisesToPdf(
    exercises: List<Exercise>,
    pageWidthPoints: Int,
    pageHeightPoints: Int
): PdfExportResult = withContext(Dispatchers.Main) {

    // Directly render from exercises; no ObjC bridge or NSDictionary needed

    val pageRect = CGRectMake(0.0, 0.0, pageWidthPoints.toDouble(), pageHeightPoints.toDouble())
    val mutableData = NSMutableData()
    UIGraphicsBeginPDFContextToData(mutableData, pageRect, null)
    UIGraphicsBeginPDFPageWithInfo(pageRect, null)

    // Layout constants
    val margin = 36.0
    val pageWidth = pageWidthPoints.toDouble()
    val pageHeight = pageHeightPoints.toDouble()
    val contentWidth = pageWidth - 2 * margin
    val maxY = pageHeight - margin

    // Text attributes
    val titleFont = UIFont.boldSystemFontOfSize(16.0)
    val bodyFont = UIFont.systemFontOfSize(12.0)
    val paragraph = NSMutableParagraphStyle().apply {
        setLineBreakMode(NSLineBreakByWordWrapping)
        setAlignment(NSTextAlignmentLeft)
    }

    var cursorY = margin

    fun ensurePageSpace(height: Double) {
        if (cursorY + height > maxY) {
            UIGraphicsBeginPDFPageWithInfo(pageRect, null)
            cursorY = margin
        }
    }

    exercises.forEach { ex ->
        val nameText = ex.name as NSString
        val descText = (ex.shortDescription ?: "") as NSString

        val nameAttrs = mapOf<Any?, Any?>(
            NSFontAttributeName to titleFont,
            NSParagraphStyleAttributeName to paragraph,
            NSForegroundColorAttributeName to UIColor.blackColor
        )
        val descAttrs = mapOf<Any?, Any?>(
            NSFontAttributeName to bodyFont,
            NSParagraphStyleAttributeName to paragraph,
            NSForegroundColorAttributeName to UIColor.darkGrayColor
        )

        val nameBounding = nameText.boundingRectWithSize(
            CGSizeMake(contentWidth, CGFLOAT_MAX),
            options = NSStringDrawingUsesLineFragmentOrigin,
            attributes = nameAttrs,
            context = null
        )
        val descBounding = descText.boundingRectWithSize(
            CGSizeMake(contentWidth, CGFLOAT_MAX),
            options = NSStringDrawingUsesLineFragmentOrigin,
            attributes = descAttrs,
            context = null
        )

        val spacing = 8.0
        val nameHeight = CGRectGetHeight(nameBounding)
        val descHeight = CGRectGetHeight(descBounding)
        val blockHeight = nameHeight + 4.0 + descHeight + spacing
        ensurePageSpace(blockHeight)

        val nameRect = CGRectMake(margin, cursorY, contentWidth, nameHeight)
        nameText.drawInRect(nameRect, withAttributes = nameAttrs)
        cursorY += nameHeight + 4.0

        val descRect = CGRectMake(margin, cursorY, contentWidth, descHeight)
        descText.drawInRect(descRect, withAttributes = descAttrs)
        cursorY += descHeight + spacing
    }
    UIGraphicsEndPDFContext()

    val pdfData: NSData? = mutableData
    val bytes = pdfData?.let {
        val length = it.length.toInt()
        val buffer = ByteArray(length)
        memcpy(buffer.refTo(0), it.bytes, it.length)
        buffer
    } ?: ByteArray(0)

    PdfExportResult("exercises.pdf", bytes)
}

