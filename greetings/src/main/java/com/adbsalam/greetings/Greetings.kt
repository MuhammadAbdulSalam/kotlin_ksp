package com.adbsalam.greetings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adbsalam.annotations.SnapIt

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
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
@SnapIt(name = "when not preview, another Should says Android")
fun GreetingPreviewSecond() {
    Greeting("Android")
}

@Preview
@Composable
@SnapIt(
    isScreen = true,
    preview = true
)
fun GreetingsScreenPreview() {
    Greeting(name = "SomeOtherPreview")
}
