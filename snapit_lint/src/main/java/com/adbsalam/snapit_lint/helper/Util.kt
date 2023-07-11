package com.adbsalam.snapit_lint.helper

const val SNAP_IT = "SnapIt"
const val PACKAGE_NAME = "com.adbsalam.annotations."
const val COMPOSABLE = "androidx.compose.runtime.Composable"

internal fun String.byPackage() = PACKAGE_NAME + this