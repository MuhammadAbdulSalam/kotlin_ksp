 package com.adbsalam.greetings

 import app.cash.paparazzi.Paparazzi
 import com.adbsalam.testing.captureScreenshot
 import com.adbsalam.testing.forScreen
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4

 @RunWith(JUnit4::class)
 class ButtonsScreenTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forScreen()

   @Test
   fun `when not in preview, should load correctly`() {
     paparazzi.captureScreenshot {
         ButtonsPreviewSecond()
     }
   }
 }



