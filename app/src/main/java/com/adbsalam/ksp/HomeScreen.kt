package com.adbsalam.ksp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adbsalam.annotations.GenerateLogs
import com.adbsalam.ksp.ui.theme.KspTheme
import com.adbsalam.printGreeting
import com.adbsalam.printGreetingPreview

@GenerateLogs(tag = "GreetingsLogs")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    printGreeting()
}

@Preview(showBackground = true)
@GenerateLogs(tag = "GreetingsPreview")
@Composable
private fun GreetingPreview() {
    KspTheme {
        Greeting("Android")
    }
    printGreetingPreview()
}