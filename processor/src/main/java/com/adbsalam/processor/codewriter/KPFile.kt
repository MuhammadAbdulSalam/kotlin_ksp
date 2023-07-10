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

    val file = FileSpec
        .builder(PACKAGE_NAME, fileName)
        .addImport(JUnit4::class, "")
        .addImport("app.cash.paparazzi", "Paparazzi")
        .addImport(PAPARAZZI_PACKAGE, "captureScreenshot")
        .addImport(PAPARAZZI_PACKAGE, "forComponent")

    functions.forEach {
        file.addImport(it.containingFile?.packageName?.asString().toString(), it.toString())
    }

    if (previewImports) {
        file
            .addImport("androidx.compose.runtime", "CompositionLocalProvider")
            .addImport("androidx.compose.ui.platform", "LocalInspectionMode")
    }
    return file
}
