package com.adbsalam.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class SnapIt(
    val name: String = "",
    val preview : Boolean = false
)