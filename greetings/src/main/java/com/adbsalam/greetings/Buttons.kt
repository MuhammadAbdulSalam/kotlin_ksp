package com.adbsalam.greetings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adbsalam.annotations.SnapIt

@SnapIt(preview = true)
@Preview
@Composable
fun ButtonsPreview() {
    Greeting("SecondGreeting")
}

@SnapIt(preview = true)
@Preview
@Composable
fun ButtonsPreviewSecond() {
    Greeting(name = "SomeOtherPreview")
}