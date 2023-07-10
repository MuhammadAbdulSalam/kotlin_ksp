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
 class GreetingsSnapTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   fun `when not preview, Should says Android`() {
     paparazzi.captureScreenshot {
         GreetingPreview()
     }
   }

   @Test
   fun `when in preview, should say SecondGreeting`() {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             GreetingPreviewSecond()
         }
     }
   }

   @Test
   fun `when in preview, should say Something`() {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             GreetingsThirdPreview()
         }
     }
   }
 }



