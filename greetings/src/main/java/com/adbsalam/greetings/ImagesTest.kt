package com.adbsalam.greetings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adbsalam.annotations.SnapItComponent

@SnapItComponent(preview = false)
@Preview
@Composable
fun ImagesPreview() {
    Greeting("SecondGreeting")
}

@SnapItComponent(preview = false)
@Preview
@Composable
fun ImagesPreviewSecond() {
    Greeting(name = "SomeOtherPreview")
}