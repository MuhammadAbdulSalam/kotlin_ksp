 package com.adbsalam

 import androidx.compose.runtime.CompositionLocalProvider
 import androidx.compose.ui.platform.LocalInspectionMode
 import app.cash.paparazzi.Paparazzi
 import com.adbsalam.greetings.GreetingPreview
 import com.adbsalam.greetings.GreetingPreviewSecond
 import com.adbsalam.greetings.GreetingsThirdPreview
 import com.adbsalam.testing.captureScreenshot
 import com.adbsalam.testing.forComponent
 import kotlin.Unit
 import kotlin.jvm.JvmField
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4

 @RunWith(JUnit4::class)
 public class GreetingsSnapTest {
   @JvmField
   @Rule
   public val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   public fun greetingPreviewSnapTest(): Unit {
     paparazzi.captureScreenshot {
         GreetingPreview()
     }
   }

   @Test
   public fun greetingPreviewSecondSnapTest(): Unit {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             GreetingPreviewSecond()
         }
     }
   }

   @Test
   public fun greetingsThirdPreviewSnapTest(): Unit {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             GreetingsThirdPreview()
         }
     }
   }
 }



