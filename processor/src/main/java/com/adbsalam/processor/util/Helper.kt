package com.adbsalam.processor.util

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSValueArgument


const val PACKAGE_NAME = "com.adbsalam"
const val FILE_NAME = "SnapShotTest"

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

internal fun fileHeader(isPreviewRequired: Boolean): String {
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
            "//class SnapShotTest { \n\n" +
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

internal val fileFooter = "//}"
internal val space4 = "    "


