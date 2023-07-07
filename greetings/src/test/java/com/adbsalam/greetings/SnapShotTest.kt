package com.adbsalam.greetings

import app.cash.paparazzi.Paparazzi
import com.adbsalam.testing.forScreen
import com.adbsalam.testing.captureScreenshot
import org.junit.Test
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class SnapShotTest { 

    @get:Rule 
    val paparazzi = Paparazzi.forScreen()

    @Test
    fun snapShotGreetingPreview() {
        paparazzi.captureScreenshot { 
            CompositionLocalProvider(LocalInspectionMode provides true) {
                GreetingPreview()
            }
        }
    }

    @Test
    fun snapShotGreetingPreviewSecond() {
        paparazzi.captureScreenshot { 
            CompositionLocalProvider(LocalInspectionMode provides true) {
                GreetingPreviewSecond()
            }
        }
    }

    @Test
    fun snapShotGreetingsThirdPreview() {
        paparazzi.captureScreenshot { 
            CompositionLocalProvider(LocalInspectionMode provides true) {
                GreetingsThirdPreview()
            }
        }
    }
}