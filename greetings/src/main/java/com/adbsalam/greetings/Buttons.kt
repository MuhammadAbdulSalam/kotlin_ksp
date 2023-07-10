package com.adbsalam.greetings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adbsalam.annotations.SnapIt


@Preview
@Composable
@SnapIt(
    name = "when in preview, should load correctly",
    preview = true
)
fun ButtonsPreview() {
    Greeting("SecondGreeting")
}

@Preview
@Composable
@SnapIt(
    name = "when not in preview, should load correctly",
    preview = false
)
fun ButtonsPreviewSecond() {
    Greeting(name = "SomeOtherPreview")
}