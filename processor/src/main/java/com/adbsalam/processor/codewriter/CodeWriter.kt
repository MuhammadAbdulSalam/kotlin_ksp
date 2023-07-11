package com.adbsalam.processor.codewriter

import com.adbsalam.annotations.SnapAnnotations
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FileSpec
import java.io.OutputStream

class CodeWriter(
    private val symbols: Sequence<KSFunctionDeclaration>,
    private val codeGenerator: CodeGenerator,
    private val resolver: Resolver,
    private val annotation: SnapAnnotations
) {

    /**
     *
     */
    fun processSymbols() {

        val fileNames = symbols.map {
            it.containingFile?.fileName ?: UNKNOWN_FILE
        }.toSet()

        fileNames.forEach { currentFile ->
            val fileName = getFileName(currentFile)
            val currentFileSymbols = symbols.filter { it.containingFile?.fileName == currentFile }
            val previewImports = currentFileSymbols.any { requirePreviewContext(it) }
            val file = createGeneratorFile(fileName = fileName)

            val codeFile = codeFile(
                previewImports,
                fileName,
                currentFileSymbols
            )

            val commentedFile = commentedFile(codeFile)

            file += commentedFile
            file.close()
        }
    }

    /**
     *
     */
    private fun getFileName(file: String): String {
        return if (annotation == SnapAnnotations.SNAP_IT) {
            file.replace(".kt", "ComponentTest")
        } else {
            file.replace(".kt", "ScreenTest")
        }
    }

    /**
     *
     */
    private fun codeFile(
        previewImports: Boolean,
        fileName: String,
        currentFileSymbols: Sequence<KSFunctionDeclaration>
    ): String {

        val kPoetFile = kFile(
            previewImports,
            fileName,
            currentFileSymbols,
            annotation
        )

        val junitClass = jUnitClass(
            fileName,
            currentFileSymbols,
            annotation
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
            .addComment(generatedCode)
            .build()
            .toString()
            .replace("package none", "")
            .replace("public ", "")
            .replace(": Unit", "")
            .replace(" import kotlin.Unit\n", "")
    }

    /**
     *
     */
    private fun createGeneratorFile(
        fileName: String
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

    operator fun OutputStream.plusAssign(str: String) {
        this.write(str.toByteArray())
    }

}