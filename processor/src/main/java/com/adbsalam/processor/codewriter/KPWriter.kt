package com.adbsalam.processor.codewriter

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FileSpec
import java.io.OutputStream

/**
 *
 */
fun processSymbols(
    codeGenerator: CodeGenerator,
    resolver: Resolver,
    symbols: Sequence<KSFunctionDeclaration>,
    annotation: AnnotationType
) {

    val fileNames = symbols.map {
        it.containingFile?.fileName ?: UNKNOWN_FILE
    }.toSet()

    fileNames.forEach { currentFile ->
        val fileName = getFileName(currentFile, annotation)
        val currentFileSymbols = symbols.filter { it.containingFile?.fileName == currentFile }
        val previewImports = currentFileSymbols.any { requirePreviewContext(it) }

        val file = createGeneratorFile(
            fileName = fileName,
            codeGenerator = codeGenerator,
            resolver = resolver
        )

        val codeFile = codeFile(
            previewImports = previewImports,
            fileName = fileName,
            symbols = currentFileSymbols,
            annotation = annotation
        )

        val commentedFile = commentedFile(codeFile)
        file += commentedFile
        file.close()
    }
}

/**
 *
 */
private fun codeFile(
    previewImports: Boolean,
    fileName: String,
    symbols: Sequence<KSFunctionDeclaration>,
    annotation: AnnotationType
): String {

    val kPoetFile = kFile(
        previewImports = previewImports,
        fileName = fileName,
        functions = symbols,
        annotation = annotation
    )

    val junitClass = jUnitClass(
        fileName = fileName,
        symbols = symbols,
        annotation = annotation
    )

    return kPoetFile
        .addType(junitClass)
        .build()
        .toString()
}

/**
 *
 */
private fun commentedFile(
    generatedCode: String
): String {
    return FileSpec
        .builder("none", "")
        .addFileComment(generatedCode)
        .build()
        .toString()
        .replaceExtras()
}

/**
 *
 */
private fun createGeneratorFile(
    fileName: String,
    codeGenerator: CodeGenerator,
    resolver: Resolver
): OutputStream {
    return codeGenerator.createNewFile(
        dependencies = Dependencies(
            aggregating = false,
            sources = resolver.getAllFiles().toList().toTypedArray()
        ),
        packageName = PACKAGE_NAME,
        fileName = fileName
    )
}

