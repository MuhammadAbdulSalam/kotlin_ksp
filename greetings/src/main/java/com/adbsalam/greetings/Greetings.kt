package com.adbsalam.greetings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adbsalam.annotations.SnapIt

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@SnapIt
@Preview
@Composable
fun GreetingPreview() {
    Greeting("Android")
}

@SnapIt(preview = true)
@Preview
@Composable
fun GreetingPreviewSecond() {
    Greeting("SecondGreeting")
}

@SnapIt(preview = true)
@Preview
@Composable
fun GreetingsThirdPreview() {
    Greeting(name = "SomeOtherPreview")
}