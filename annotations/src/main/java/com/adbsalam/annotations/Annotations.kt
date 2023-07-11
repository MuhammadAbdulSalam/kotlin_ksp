package com.adbsalam.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class SnapIt(
    val name: String = "",
    val isScreen : Boolean = false,
    val preview : Boolean = false
)