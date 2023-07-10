package com.adbsalam.processor.codewriter

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument
import com.squareup.kotlinpoet.FunSpec
import org.junit.Test
import java.util.Locale

/**
 *
 */
internal fun snapFunctions(
    functions: Sequence<KSFunctionDeclaration>
): Iterable<FunSpec> {
    return functions.map { kFun ->
        if (requirePreviewContext(kFun)) {
            previewFuncSpec(kFun)
        } else {
            nonPreviewFuncSpec(kFun)
        }
    }.asIterable()
}

/**
 *
 */
internal fun nonPreviewFuncSpec(
    function: KSFunctionDeclaration
): FunSpec {
    return FunSpec.builder(getMethodName(function))
        .addAnnotation(Test::class)
        .addCode(
            """
                |paparazzi.captureScreenshot {
                |    $function()
                |}
                """.trimMargin()
        )
        .build()
}

/**
 *
 */
internal fun previewFuncSpec(
    function: KSFunctionDeclaration
): FunSpec {
    return FunSpec.builder(getMethodName(function))
        .addAnnotation(Test::class)
        .addCode(
            """
                |paparazzi.captureScreenshot {
                |    CompositionLocalProvider(LocalInspectionMode provides true) {
                |        $function()
                |    }
                |}
                """.trimMargin()
        )
        .build()
}

fun getMethodName(
    function: KSFunctionDeclaration
): String {
    val annotation: KSAnnotation = function.annotations.first {
        it.shortName.asString() == "SnapIt"
    }

    val tagArg: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "name" }

    val value = tagArg.value as String

    if (value.isNotEmpty()) {
        return value
    }

    val functionName = function.toString().replaceFirst(
        oldChar = function.toString().first(),
        newChar = function.toString().first().lowercaseChar()
    )

    return value.ifEmpty { "${functionName}SnapTest" }
}