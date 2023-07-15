import androidx.compose.runtime.Composable
import app.cash.paparazzi.Paparazzi
import com.adbsalam.testing.forScreen
import org.junit.Test
import com.adbsalam.greetings.GreetingPreview
import com.adbsalam.greetings.GreetingPreviewSecond
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.reflect.KFunction

public class SnapShotTest() { 

   @get:Rule val paparazzi = Paparazzi.forScreen()

   @Test
   fun snapShotGreetingPreview(){
       paparazzi.snapshot { 
           GreetingPreview() 
       }
   }

   @Test
   fun snapShotGreetingPreviewSecond(){
       paparazzi.snapshot { 
           GreetingPreviewSecond() 
       }
   }

 }