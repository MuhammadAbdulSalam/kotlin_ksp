@file:Suppress("UnnecessaryVariable")

package com.adbsalam.processor

import com.adbsalam.annotations.ANNOTATION_PACKAGE_NAME
import com.adbsalam.processor.util.PACKAGE_NAME
import com.adbsalam.processor.util.fileFooter
import com.adbsalam.processor.util.fileHeader
import com.adbsalam.processor.util.fileName
import com.adbsalam.processor.util.requirePreviewContext
import com.adbsalam.processor.util.testMethods
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.symbol.Variance.*
import com.google.devtools.ksp.validate
import java.io.OutputStream

class FunctionProcessor(
    private val options: Map<String, String>,
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {

    operator fun OutputStream.plusAssign(str: String) {
        this.write(str.toByteArray())
    }

    override fun process(
        resolver: Resolver
    ): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation(ANNOTATION_PACKAGE_NAME)
            .filterIsInstance<KSFunctionDeclaration>()

        if (!symbols.iterator().hasNext()) return emptyList()

        val fileName = fileName(symbols.first())

        val file = codeGenerator.createNewFile(
            dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray()),
            packageName = PACKAGE_NAME,
            fileName = fileName
        )

        file += fileHeader(
            isPreviewRequired = symbols.any { requirePreviewContext(it) },
            fileName = fileName
        )
        symbols.forEach { it.accept(Visitor(file), Unit) }
        file += fileFooter
        file.close()
        return symbols.filterNot { it.validate() }.toList()
    }

    inner class Visitor(
        private val file: OutputStream
    ) : KSVisitorVoid() {
        override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
            super.visitFunctionDeclaration(function, data)
            file += testMethods(function)
        }
    }
}