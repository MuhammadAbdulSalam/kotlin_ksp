 package com.adbsalam

 import app.cash.paparazzi.Paparazzi
 import com.adbsalam.greetings.ImagesPreview
 import com.adbsalam.greetings.ImagesPreviewSecond
 import com.adbsalam.testing.captureScreenshot
 import com.adbsalam.testing.forComponent
 import kotlin.Unit
 import kotlin.jvm.JvmField
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4

 @RunWith(JUnit4::class)
 public class ImagesTestSnapTest {
   @JvmField
   @Rule
   public val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   public fun imagesPreviewSnapTest(): Unit {
     paparazzi.captureScreenshot {
         ImagesPreview()
     }
   }

   @Test
   public fun imagesPreviewSecondSnapTest(): Unit {
     paparazzi.captureScreenshot {
         ImagesPreviewSecond()
     }
   }
 }



