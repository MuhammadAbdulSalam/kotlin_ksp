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
            .getSymbolsWithAnnotation("com.adbsalam.annotations.SnapIt")
            .filterIsInstance<KSFunctionDeclaration>()

        if (!symbols.iterator().hasNext()) return emptyList()

        val file = codeGenerator.createNewFile(
            dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray()),
            packageName = "com.adbsalam",
            fileName = "SnapShotTest"
        )

        //TODO use if needed
//        val functionImports = symbols.map {
//            "//import com.adbsalam.greetings.${it}"
//        }
//        functionImports.joinToString(separator = "\n") +


        file += "//package com.adbsalam.greetings\n\n" +
                "//import app.cash.paparazzi.Paparazzi\n" +
                "//import com.adbsalam.testing.forScreen\n" +
                "//import org.junit.Test\n" +
                "//import org.junit.Assert.*\n" +
                "//import org.junit.Rule\n" +
                "//import org.junit.runner.RunWith\n" +
                "//import org.junit.runners.JUnit4\n\n\n" +
                "//@RunWith(JUnit4::class)\n" +
                "//class SnapShotTest { \n\n" +
                "    //@get:Rule \n" +
                "    //val paparazzi = Paparazzi.forScreen()\n"

        symbols.forEach { it.accept(Visitor(file), Unit) }

        file += "//}"
        file.close()
        return symbols.filterNot { it.validate() }.toList()
    }

    inner class Visitor(private val file: OutputStream) : KSVisitorVoid() {

        override fun visitFunctionDeclaration(function: KSFunctionDeclaration, data: Unit) {
            super.visitFunctionDeclaration(function, data)

            val annotation: KSAnnotation = function.annotations.first {
                it.shortName.asString() == "SnapIt"
            }

//            val tagArg: KSValueArgument =
//                annotation.arguments.first { arg -> arg.name?.asString() == "file" }
//
//            val tag = tagArg.value as String

            val args = function.parameters

            var paramNames = ""

            args.forEach {
                paramNames += ", $it "
            }

            file += "\n"
            file += "    //@Test\n"
            file += "    //fun snapShot${function}(){\n"
            file += "        //paparazzi.snapshot { \n"
            file += "           //$function() \n"
            file += "        //}\n"
            file += "    //}\n"
        }
    }
}