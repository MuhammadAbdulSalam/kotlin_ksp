package com.adbsalam.processor.codewriter

import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FileSpec
import org.junit.runners.JUnit4

/**
 *
 */
internal fun kFile(
    previewImports: Boolean,
    fileName: String,
    functions: Sequence<KSFunctionDeclaration>
): FileSpec.Builder {

    val packageName = functions.first().containingFile?.packageName?.asString().toString()

    val file = FileSpec
        .builder(packageName, fileName)
        .addImport(JUnit4::class, "")
        .addImport("app.cash.paparazzi", "Paparazzi")
        .addImport(PAPARAZZI_PACKAGE, "captureScreenshot")
        .addImport(PAPARAZZI_PACKAGE, "forComponent")

    if (previewImports) {
        file
            .addImport("androidx.compose.runtime", "CompositionLocalProvider")
            .addImport("androidx.compose.ui.platform", "LocalInspectionMode")
    }
    return file
}
