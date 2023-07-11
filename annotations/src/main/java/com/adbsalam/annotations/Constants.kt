package com.adbsalam.annotations

enum class SnapAnnotations(
    val annotation: String,
    val packageName: String
) {
    SNAP_IT_COMPONENT(
        "SnapItComponent",
        "com.adbsalam.annotations.SnapItComponent"
    ),
    SNAP_IT_SCREEN(
        "SnapItScreen",
        "com.adbsalam.annotations.SnapItScreen"
    )
}
