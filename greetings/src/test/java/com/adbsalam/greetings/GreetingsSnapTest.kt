 package com.adbsalam.greetings

 import androidx.compose.runtime.CompositionLocalProvider
 import androidx.compose.ui.platform.LocalInspectionMode
 import app.cash.paparazzi.Paparazzi
 import com.adbsalam.testing.captureScreenshot
 import com.adbsalam.testing.forComponent
 import kotlin.jvm.JvmField
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4

 @RunWith(JUnit4::class)
 class GreetingsSnapTest {
   @JvmField
   @Rule
   val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   fun greetingPreviewSnapTest() {
     paparazzi.captureScreenshot {
         GreetingPreview()
     }
   }

   @Test
   fun greetingPreviewSecondSnapTest() {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             GreetingPreviewSecond()
         }
     }
   }

   @Test
   fun greetingsThirdPreviewSnapTest() {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             GreetingsThirdPreview()
         }
     }
   }
 }



