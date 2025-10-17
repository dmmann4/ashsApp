//
// Created by Mann Fam on 10/8/25.
//

// iosApp/PdfGenerator.swift

import Foundation
import UIKit
import CoreGraphics

@objc public class PdfGenerator: NSObject {
    @objc public static func generatePdf(_ exercises: [[String: Any]], pageWidth: Double, pageHeight: Double) -> Data? {
        let pageSize = CGSize(width: CGFloat(pageWidth), height: CGFloat(pageHeight))
        let pdfData = NSMutableData()
        UIGraphicsBeginPDFContextToData(pdfData, CGRect(origin: .zero, size: pageSize), nil)

        let margin: CGFloat = 24
        let imageWidth = pageSize.width * 0.28
        let imageHeight = pageSize.height * 0.22
        let textStartX = margin + imageWidth + margin
        let maxTextWidth = pageSize.width - textStartX - margin
        let blockSpacing: CGFloat = 16

        var currentY: CGFloat = margin

        for item in exercises {
            if currentY + imageHeight + margin > pageSize.height {
                UIGraphicsBeginPDFPage()
                currentY = margin
            } else {
                UIGraphicsBeginPDFPage()
            }

            // Image left
            if let data = item["imageData"] as? Data, let image = UIImage(data: data) {
                let imgRect = CGRect(x: margin, y: currentY, width: imageWidth, height: imageHeight)
                image.draw(in: imgRect)
            }

            // Title
            var textY = currentY
            if let name = item["name"] as? String {
                let titleFont = UIFont.boldSystemFont(ofSize: 16)
                let titleAttr = NSAttributedString(string: name, attributes: [
                    .font: titleFont,
                    .foregroundColor: UIColor.black
                ])
                let titleRect = CGRect(x: textStartX, y: textY, width: maxTextWidth, height: 200)
                titleAttr.draw(in: titleRect)
                textY += titleFont.lineHeight + 6
            }

            // Description
            if let desc = item["description"] as? String {
                let descFont = UIFont.systemFont(ofSize: 12)
                let descAttr = NSAttributedString(string: desc, attributes: [
                    .font: descFont,
                    .foregroundColor: UIColor.darkText
                ])
                let descRect = CGRect(x: textStartX, y: textY, width: maxTextWidth, height: pageSize.height - textY - margin)
                descAttr.draw(in: descRect)
            }

            let blockHeight = max(imageHeight, 120)
            currentY += blockHeight + blockSpacing
        }

        UIGraphicsEndPDFContext()
        return pdfData as Data
    }
}
