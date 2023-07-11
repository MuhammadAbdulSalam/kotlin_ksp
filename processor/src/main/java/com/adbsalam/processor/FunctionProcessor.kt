package com.adbsalam.processor

import com.adbsalam.annotations.SNAP_IT_PACKAGE
import com.adbsalam.processor.codewriter.AnnotationType
import com.adbsalam.processor.codewriter.isScreenComponent
import com.adbsalam.processor.codewriter.processSymbols
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
            .getSymbolsWithAnnotation(SNAP_IT_PACKAGE)
            .filterIsInstance<KSFunctionDeclaration>()

        if (!symbols.iterator().hasNext()) return emptyList()

        val componentSymbol = symbols.filter {
            !isScreenComponent(it)
        }

        val screenSymbols = symbols.filter {
            isScreenComponent(it)
        }

        if (componentSymbol.iterator().hasNext()) {
            processSymbols(
                symbols = componentSymbol,
                codeGenerator = codeGenerator,
                resolver = resolver,
                annotation = AnnotationType.COMPONENT
            )
        }

        if (screenSymbols.iterator().hasNext()) {
            processSymbols(
                symbols = screenSymbols,
                codeGenerator = codeGenerator,
                resolver = resolver,
                annotation = AnnotationType.SCREEN
            )
        }

        return symbols.filterNot { it.validate() }.toList()
    }
}