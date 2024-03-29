package com.adbsalam.processor.codewriter

import com.adbsalam.annotations.SNAP_IT
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument

/**
 * @param function function to be processed
 * @return True if function annotation contains preview = true
 * to allow the writer know this functions needs to be processed as a preview function
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

/**
 * @param function function to be processed
 * @return True if function annotation contains isScreen = true
 * This is to allow writer know generated code needs to have screen paparazzi instance
 **/
internal fun isScreenComponent(
    function: KSFunctionDeclaration,
): Boolean {

    val annotation: KSAnnotation = function.annotations.first {
        it.shortName.asString() == SNAP_IT
    }

    val tagArg: KSValueArgument =
        annotation.arguments.first { arg -> arg.name?.asString() == "isScreen" }

    return tagArg.value as Boolean
}

