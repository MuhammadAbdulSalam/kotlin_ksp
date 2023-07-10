 package com.adbsalam

 import androidx.compose.runtime.CompositionLocalProvider
 import androidx.compose.ui.platform.LocalInspectionMode
 import app.cash.paparazzi.Paparazzi
 import com.adbsalam.greetings.ButtonsPreview
 import com.adbsalam.greetings.ButtonsPreviewSecond
 import com.adbsalam.testing.captureScreenshot
 import com.adbsalam.testing.forComponent
 import kotlin.Unit
 import kotlin.jvm.JvmField
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4

 @RunWith(JUnit4::class)
 public class ButtonsSnapTest {
   @JvmField
   @Rule
   public val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   public fun `when in preview, should load correctly`(): Unit {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             ButtonsPreview()
         }
     }
   }

   @Test
   public fun `when not in preview, should load correctly`(): Unit {
     paparazzi.captureScreenshot {
         ButtonsPreviewSecond()
     }
   }
 }



