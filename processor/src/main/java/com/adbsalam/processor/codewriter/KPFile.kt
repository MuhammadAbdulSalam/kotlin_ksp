package com.adbsalam.processor.codewriter

import com.adbsalam.processor.codewriter.AnnotationType.COMPONENT
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FileSpec
import org.junit.runners.JUnit4

/**
 *
 */
internal fun kFile(
    previewImports: Boolean,
    fileName: String,
    functions: Sequence<KSFunctionDeclaration>,
    annotation: AnnotationType
): FileSpec.Builder {

    val packageName = functions.first().containingFile?.packageName?.asString().toString()

    val file = FileSpec
        .builder(packageName, fileName)
        .addImport(JUnit4::class, "")
        .addImport("app.cash.paparazzi", "Paparazzi")
        .addImport(PAPARAZZI_PACKAGE, "captureScreenshot")
        .addImport(PAPARAZZI_PACKAGE, paparazziInstanceImport(annotation))

    if (previewImports) {
        file
            .addImport("androidx.compose.runtime", "CompositionLocalProvider")
            .addImport("androidx.compose.ui.platform", "LocalInspectionMode")
    }
    return file
}

private fun paparazziInstanceImport(annotation: AnnotationType): String =
    if (annotation == COMPONENT) "forComponent" else "forScreen"
