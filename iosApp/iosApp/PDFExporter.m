//
// Created by Mann Fam on 10/8/25.
//

#import "PDFExporter.h"
#import "PerfectFitKPM-Swift.h"

@implementation PdfGeneratorObjC
+ (NSData *)generatePdfWithExercises:(NSArray<NSDictionary *> *)exercises pageWidth:(double)pageWidth pageHeight:(double)pageHeight {
    return [PdfGenerator generatePdf:exercises pageWidth:pageWidth pageHeight:pageHeight];
}
@end
