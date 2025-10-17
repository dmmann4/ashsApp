//
// Created by Mann Fam on 10/8/25.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface PdfGeneratorObjC : NSObject
+ (nullable NSData *)generatePdfWithExercises:(NSArray<NSDictionary *> *)exercises
        pageWidth:(double)pageWidth
        pageHeight:(double)pageHeight;
@end

NS_ASSUME_NONNULL_END
