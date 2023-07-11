package com.adbsalam.processor.annotation

const val ANNOTATION_PACKAGE_NAME = "com.adbsalam.processor.annotation"

@Target(AnnotationTarget.FUNCTION)
annotation class SnapIt(
    val name: String = "",
    val preview : Boolean = false
)