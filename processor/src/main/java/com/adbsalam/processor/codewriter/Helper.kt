package com.adbsalam.processor.codewriter

import java.io.OutputStream

const val PACKAGE_NAME = "com.adbsalam"
const val UNKNOWN_FILE = "UnknownFileName"
const val PAPARAZZI_PACKAGE = "com.adbsalam.testing"

/**
 * Annotation type as created
 * @SnapIt(isScreen = true) -> SCREEN
 * @SnapIt(isScreen = false) -> COMPONENT
 */
enum class AnnotationType {
    SCREEN, COMPONENT
}

/**
 * @param file current file in process, file name will be as Example.kt
 * @param annotation type of annotation being processes
 * i.e @SnapIt(isScreen = true) or @SnapIt(isScreen = false)
 *
 * @return returns file name based on annotation being used,
 * this file name will be used to name the test file
 */
internal fun getFileName(
    file: String,
    annotation: AnnotationType
): String {
    return if (annotation == AnnotationType.COMPONENT) {
        file.replace(".kt", "ComponentTest")
    } else {
        file.replace(".kt", "ScreenTest")
    }
}

/**
 * @param str string to be written
 * general purpose operator function to add to file
 */
internal operator fun OutputStream.plusAssign(str: String) {
    this.write(str.toByteArray())
}

/**
 * Replace extra code from the file thats not needed
 * Some of this code is generated by kotlin poet but is not needed
 * Thus clean the file for any extra code
 */
internal fun String.replaceExtras(): String {
    return this.replace("package none", "")
        .replace("public ", "")
        .replace(": Unit", "")
        .replace(" import kotlin.Unit\n", "")
}