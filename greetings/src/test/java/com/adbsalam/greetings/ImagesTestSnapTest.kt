 package com.adbsalam.greetings

 import app.cash.paparazzi.Paparazzi
 import com.adbsalam.testing.captureScreenshot
 import com.adbsalam.testing.forComponent
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4

 @RunWith(JUnit4::class)
 class ImagesTestSnapTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   fun imagesPreviewSnapTest() {
     paparazzi.captureScreenshot {
         ImagesPreview()
     }
   }

   @Test
   fun imagesPreviewSecondSnapTest() {
     paparazzi.captureScreenshot {
         ImagesPreviewSecond()
     }
   }
 }



