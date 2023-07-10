package com.adbsalam.greetings

import app.cash.paparazzi.Paparazzi
import com.adbsalam.testing.captureScreenshot
import com.adbsalam.testing.forComponent
import org.junit.Test
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ImagesTest { 

    @get:Rule 
    val paparazzi = Paparazzi.forComponent()

    @Test
    fun snapShotImagesPreview() {
        paparazzi.captureScreenshot { 
            CompositionLocalProvider(LocalInspectionMode provides true) {
                ImagesPreview()
            }
        }
    }

    @Test
    fun snapShotImagesPreviewSecond() {
        paparazzi.captureScreenshot { 
            CompositionLocalProvider(LocalInspectionMode provides true) {
                ImagesPreviewSecond()
            }
        }
    }
}