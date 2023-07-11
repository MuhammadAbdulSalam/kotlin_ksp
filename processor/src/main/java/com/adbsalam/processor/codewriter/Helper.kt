package com.adbsalam.processor.codewriter

import java.io.OutputStream

const val PACKAGE_NAME = "com.adbsalam"
const val UNKNOWN_FILE = "UnknownFileName"
const val PAPARAZZI_PACKAGE = "com.adbsalam.testing"

enum class AnnotationType {
    SCREEN, COMPONENT
}

/**
 *
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

internal operator fun OutputStream.plusAssign(str: String) {
    this.write(str.toByteArray())
}

internal fun String.replaceExtras(): String {
    return this.replace("package none", "")
        .replace("public ", "")
        .replace(": Unit", "")
        .replace(" import kotlin.Unit\n", "")
}