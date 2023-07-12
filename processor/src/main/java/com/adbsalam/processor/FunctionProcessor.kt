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

/**
 * @param logger to present logs from processor
 * @param codeGenerator code generator context to create new files
 *
 */
class FunctionProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {

    /**
     * Process annotations and create new code files
     * If no annotations exist then return empty array
     * Filter through symbols and separate out functions based on preview: param
     * Processed preview and non preview functions separately
     */
    override fun process(
        resolver: Resolver
    ): List<KSAnnotated> {

        val symbols = resolver
            .getSymbolsWithAnnotation(SNAP_IT_PACKAGE)
            .filterIsInstance<KSFunctionDeclaration>()

        if (!symbols.iterator().hasNext()) {
            logger.info("No Symbols to be processed")
            return emptyList()
        }

        val componentSymbol = symbols.filter { !isScreenComponent(it) }

        if (componentSymbol.iterator().hasNext()) {
            processSymbols(
                symbols = componentSymbol,
                codeGenerator = codeGenerator,
                resolver = resolver,
                annotation = AnnotationType.COMPONENT
            )
        }

        val screenSymbols = symbols.filter { isScreenComponent(it) }

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