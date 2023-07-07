package com.adbsalam.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class SnapIt(val preview : Boolean = false)