package com.adbsalam.greetings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adbsalam.annotations.ScreenSnapIt
import com.adbsalam.annotations.SnapIt

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview
@Composable
@SnapIt(name = "when not preview, Should says Android")
fun GreetingPreview() {
    Greeting("Android")
}

@Preview
@Composable
@ScreenSnapIt(name = "when in preview, should say Something", preview = true)
fun GreetingsThirdPreview() {
    Greeting(name = "SomeOtherPreview")
}
