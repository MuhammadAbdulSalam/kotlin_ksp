package com.adbsalam.processor.codewriter

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument

const val PACKAGE_NAME = "com.adbsalam"
const val UNKNOWN_FILE = "UnknownFileName"
const val PAPARAZZI_PACKAGE = "com.adbsalam.testing"

/**
 *
 */
internal fun requirePreviewContext(
    function: KSFunctionDeclaration
): Boolean {
    val annotation: KSAnnotation = function.annotations.first {
        it.shortName.asString() == "SnapIt"
    }

    val tagArg: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "preview" }

    return tagArg.value as Boolean
}

