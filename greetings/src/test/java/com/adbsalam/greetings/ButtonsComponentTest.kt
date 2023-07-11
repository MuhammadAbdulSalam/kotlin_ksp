 package com.adbsalam.greetings

 import androidx.compose.runtime.CompositionLocalProvider
 import androidx.compose.ui.platform.LocalInspectionMode
 import app.cash.paparazzi.Paparazzi
 import com.adbsalam.testing.captureScreenshot
 import com.adbsalam.testing.forComponent
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4

 @RunWith(JUnit4::class)
 class ButtonsComponentTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   fun buttonsPreviewSnapTest() {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             ButtonsPreview()
         }
     }
   }
 }



