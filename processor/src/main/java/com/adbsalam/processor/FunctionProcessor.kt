package com.adbsalam.processor

import com.adbsalam.annotations.SnapAnnotations
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

        val componentSymbol = resolver
            .getSymbolsWithAnnotation(SnapAnnotations.SNAP_IT_COMPONENT.packageName)
            .filterIsInstance<KSFunctionDeclaration>()

        val screenSymbol = resolver
            .getSymbolsWithAnnotation(SnapAnnotations.SNAP_IT_SCREEN.packageName)
            .filterIsInstance<KSFunctionDeclaration>()

        if (!componentSymbol.iterator().hasNext()) return emptyList()

        //Handle component symbols
        CodeWriter(
            symbols = componentSymbol,
            codeGenerator = codeGenerator,
            resolver = resolver,
            annotation = SnapAnnotations.SNAP_IT_COMPONENT
        ).processSymbols()

        //Handle screen symbols
        if (screenSymbol.iterator().hasNext()) {
            CodeWriter(
                symbols = screenSymbol,
                codeGenerator = codeGenerator,
                resolver = resolver,
                annotation = SnapAnnotations.SNAP_IT_SCREEN
            ).processSymbols()
        }

        val symbols = componentSymbol + screenSymbol
        return symbols.filterNot { it.validate() }.toList()
    }

}