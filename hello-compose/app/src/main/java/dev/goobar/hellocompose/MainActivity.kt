package dev.goobar.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {

//  Uncomment the code below, and rebuild, once Compose dependencies are added to the project

//      MaterialTheme {
//        // A surface container using the 'background' color from the theme
//        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//          Text(text = "Hello Jetpack Compose!")
//        }
//      }
    }
  }

}