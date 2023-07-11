package com.adbsalam.annotations

enum class SnapAnnotations(
    val annotation: String,
    val packageName: String
) {
    SNAP_IT(
        "SnapIt",
        "com.adbsalam.annotations.SnapIt"
    ),

    SNAP_IT_SCREEN(
        "ScreenSnapIt",
        "com.adbsalam.annotations.ScreenSnapIt"
    )
}
