package com.adbsalam.processor.codewriter

import com.adbsalam.annotations.SNAP_IT
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument

/**
 *
 */
internal fun requirePreviewContext(
    function: KSFunctionDeclaration,
): Boolean {
    val annotation: KSAnnotation = function.annotations.first {
        it.shortName.asString() == SNAP_IT
    }

    val tagArg: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "preview" }

    return tagArg.value as Boolean
}

internal fun isScreenComponent(
    function: KSFunctionDeclaration,
): Boolean{

    val annotation: KSAnnotation = function.annotations.first {
        it.shortName.asString() == SNAP_IT
    }

    val tagArg: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "isScreen" }

    return tagArg.value as Boolean
}

