@file:Suppress("UnnecessaryVariable")

package com.adbsalam.processor

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

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver
            .getSymbolsWithAnnotation("com.adbsalam.annotations.GenerateLogs")
            .filterIsInstance<KSFunctionDeclaration>()

        if (!symbols.iterator().hasNext()) return emptyList()

        val file = codeGenerator.createNewFile(
            dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray()),
            packageName = "com.adbsalam",
            fileName = "GeneratedLogs"
        )

        file += "package com.adbsalam\n"
        symbols.forEach { it.accept(Visitor(file), Unit) }
        file.close()
        return symbols.filterNot { it.validate() }.toList()
    }

    inner class Visitor(private val file: OutputStream) : KSVisitorVoid() {


        override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
            super.visitFunctionDeclaration(function, data)

            val annotation: KSAnnotation = function.annotations.first {
                it.shortName.asString() == "GenerateLogs"
            }

            val tagArg: KSValueArgument =
                annotation.arguments.first { arg -> arg.name?.asString() == "tag" }

            val tag = tagArg.value as String

            val args = function.parameters

            var paramNames = ""

            args.forEach {
                paramNames += ", $it "
            }

            file += "\n"
            file += "fun print${function}(){\n"
            file += "   println( \" $tag ##-> ${function}() have ${args.size} parameters, $paramNames\" )\n"
            file += "}\n"
        }
    }
}