package dev.goobar.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {

      MaterialTheme {
        MainActivityContent()
      }
    }
  }

  @Composable
  private fun MainActivityContent() {
    Scaffold(
      topBar = {
        TopAppBar(contentPadding = PaddingValues(horizontal = 20.dp)) {
          Text("Hello Jetpack Compose")
        }
      }
    ) {
      LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        items(AndroidVersionsRepository.data) { info ->
          AndroidVersionInfoCard(info)
        }
      }
    }
  }

  @Composable
  private fun AndroidVersionInfoCard(info: AndroidVersionInfo) {
    Card(modifier = Modifier
      .fillMaxWidth()
      .defaultMinSize(minHeight = 120.dp)
    ) {
      Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Image(
          modifier = Modifier.size(64.dp),
          painter = painterResource(id = R.drawable.ic_android),
          contentDescription = "Android icon"
        )
        Column(modifier = Modifier.padding(start = 20.dp)) {
          Text(text = info.publicName, style = MaterialTheme.typography.h4)
          Text(text = "${info.codename} - API ${info.apiVersion}")
          Text(
            modifier = Modifier.padding(top = 4.dp),
            text = info.details,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
          )
        }
      }
    }
  }

}