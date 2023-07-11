package com.adbsalam.greetings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adbsalam.annotations.SnapItComponent
import com.adbsalam.annotations.SnapItScreen


@Preview
@Composable
@SnapItComponent(name = "when in preview, should load correctly", preview = true)
fun ButtonsPreview() {
    Greeting("SecondGreeting")
}

@Preview
@Composable
@SnapItScreen(name = "when not in preview, should load correctly")
fun ButtonsPreviewSecond() {
    Greeting(name = "SomeOtherPreview")
}