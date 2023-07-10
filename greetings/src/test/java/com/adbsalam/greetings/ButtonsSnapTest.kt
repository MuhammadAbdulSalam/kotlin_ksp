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
 class ButtonsSnapTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   fun `when in preview, should load correctly`() {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             ButtonsPreview()
         }
     }
   }

   @Test
   fun `when not in preview, should load correctly`() {
     paparazzi.captureScreenshot {
         ButtonsPreviewSecond()
     }
   }
 }



