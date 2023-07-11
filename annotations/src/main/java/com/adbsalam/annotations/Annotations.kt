package com.adbsalam.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class SnapItComponent(
    val name: String = "",
    val preview : Boolean = false
)

@Target(AnnotationTarget.FUNCTION)
annotation class SnapItScreen(
    val name: String = "",
    val preview : Boolean = false
)