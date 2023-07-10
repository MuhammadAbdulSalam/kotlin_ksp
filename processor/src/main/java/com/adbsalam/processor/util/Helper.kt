package com.adbsalam.processor.util

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument


const val PACKAGE_NAME = "com.adbsalam"
const val TEMP_FILE_NAME = "SnapShotTest"
const val UNKNOWN_FILE = "UnknownFileName"

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

internal fun previewImports(isPreviewRequired: Boolean): String {
    return if (isPreviewRequired) {
        "//import androidx.compose.runtime.CompositionLocalProvider\n" +
                "//import androidx.compose.ui.platform.LocalInspectionMode\n"
    } else {
        ""
    }
}

internal fun fileHeader(isPreviewRequired: Boolean, fileName: String): String {
    return "//package com.adbsalam.greetings\n\n" +
            "//import app.cash.paparazzi.Paparazzi\n" +
            "//import com.adbsalam.testing.captureScreenshot\n" +
            "//import com.adbsalam.testing.forComponent\n" +
            "//import org.junit.Test\n" +
            previewImports(isPreviewRequired) +
            "//import org.junit.Assert.*\n" +
            "//import org.junit.Rule\n" +
            "//import org.junit.runner.RunWith\n" +
            "//import org.junit.runners.JUnit4\n\n" +
            "//@RunWith(JUnit4::class)\n" +
            "//class $fileName { \n\n" +
            "$space4//@get:Rule \n" +
            "$space4//val paparazzi = Paparazzi.forComponent()\n"
}

fun testMethods(function: KSFunctionDeclaration): String {
    val previewContextCmd = if (requirePreviewContext(function)) {
        "$space4$space4$space4//CompositionLocalProvider(LocalInspectionMode provides true) {\n" +
                "$space4$space4$space4$space4//$function()\n" +
                "$space4$space4$space4//}\n"
    } else {
        "$space4$space4$space4//$function() \n"
    }
    return "\n" +
            "$space4//@Test\n" +
            "$space4//fun snapShot${function}() {\n" +
            "$space4$space4//paparazzi.captureScreenshot { \n" +
            previewContextCmd +
            "$space4$space4//}\n" +
            "$space4//}\n"
}

fun fileName(function: KSFunctionDeclaration): String {
    return function.containingFile?.fileName?.replace(".kt", "SnapTest")?: TEMP_FILE_NAME
}
internal const val fileFooter = "//}"
internal const val space4 = "    "


