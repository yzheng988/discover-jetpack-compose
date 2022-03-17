package dev.goobar.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.goobar.hellocompose.androidversiondetails.AndroidVersionDetails
import dev.goobar.hellocompose.androidversionslist.AndroidVersionsList
import dev.goobar.hellocompose.design.HelloComposeTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HelloComposeTheme() {
        MainActivityContent()
      }
    }
  }

  @Composable
  private fun MainActivityContent() {

    var selectedItem by remember { mutableStateOf<AndroidVersionInfo?>(null) }

    Scaffold(
      topBar = {
        MainAppBar(
          selectedItem = selectedItem,
          onBackClick = { selectedItem = null }
        )
      }
    ) {
      when (val currentItem = selectedItem) {
        null -> AndroidVersionsList(
          versions = AndroidVersionsRepository.data,
          onClick = { clickedInfo -> selectedItem = clickedInfo }
        )
        else -> AndroidVersionDetails(
          info = currentItem,
          onBackClick = { selectedItem = null }
        )
      }
    }
  }

  @Composable
  private fun MainAppBar(selectedItem: AndroidVersionInfo?, onBackClick: () -> Unit) {
    TopAppBar(
      contentPadding = PaddingValues(horizontal = 20.dp),
    ) {
      when (selectedItem) {
        null -> {
          Text("Hello Jetpack Compose")
        }
        else -> {
          IconButton(onClick = onBackClick) {
            Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back arrow")
          }
          Text(text = selectedItem.publicName, modifier = Modifier.padding(start = 20.dp))
        }
      }
    }
  }
}