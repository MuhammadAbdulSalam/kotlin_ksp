package com.adbsalam.processor

import com.adbsalam.annotations.ANNOTATION_PACKAGE_NAME
import com.adbsalam.processor.codewriter.CodeWriter
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
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

        val codeWriter = CodeWriter(
            symbols = symbols,
            codeGenerator = codeGenerator,
            resolver = resolver,
        )

        codeWriter.processSymbols()

        return symbols.filterNot { it.validate() }.toList()
    }

}