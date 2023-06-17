package org.northsea40.practiceapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.northsea40.practiceapplication.ui.theme.PracticeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {    // Step 1
            PracticeApplicationTheme {  // step M01 (Material Design)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")     // step 2
                    MessageCard(Message("Android", "Jetpack Compose"))  // step 6
                }
            }   // Material Design is built around three pillars:
                // Color, Typography, and Shape.
        }
    }
}

data class Message(val author: String, val body: String)    // step 4

@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp))/* step 10 */ {   // step 8
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)  // step 11
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)    // stop M03
        )   // step 9

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp)) // step 12

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium    // step M04
            )

            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))    // step 13

            Text(text = msg.body)
        }   // step 7

        Spacer(modifier = Modifier.height(4.dp))

        Surface(shape = MaterialTheme.shapes.medium, tonalElevation = 1.dp) {
            Text(
                text = msg.body,
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }   // step M05
    }
}

//@Composable
//fun MessageCard(msg: Message) {
//    Text(text = msg.author)
//    Text(text = msg.body)
//}   // step 5

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}   // step 3

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    PracticeApplicationTheme {
//        Greeting("Android")
//    }
//}   // step 3a (This is only for preview)

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)   // step M06
@Composable
fun PreviewMessageCard() {
    PracticeApplicationTheme {
        Surface {
            MessageCard(
                msg = Message("Colleague", "Take a look at Jetpack Compose, it's great!")
            )
        }
    }   // step M02
}