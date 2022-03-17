package dev.goobar.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion
import androidx.compose.ui.unit.dp
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
        TopAppBar(
          contentPadding = PaddingValues(horizontal = 20.dp),
        ) {
          when (val currentItem = selectedItem) {
            null -> {
              Text("Hello Jetpack Compose")
            }
            else -> {
              IconButton(onClick = { selectedItem = null }) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back arrow")
              }
              Text(text = currentItem.publicName, modifier = Modifier.padding(start = 20.dp))
            }
          }
        }
      }
    ) {
      when (val currentItem = selectedItem) {
        null -> AndroidVersionsList() { clickedInfo ->
          selectedItem = clickedInfo
        }
        else -> AndroidVersionDetails(currentItem) {
          selectedItem = null
        }
      }
    }
  }

  @Composable
  private fun AndroidVersionsList(onClick: (AndroidVersionInfo) -> Unit) {
    LazyColumn(
      contentPadding = PaddingValues(20.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(AndroidVersionsRepository.data) { info ->
        AndroidVersionInfoCard(info, onClick)
      }
    }
  }

  @Composable
  private fun AndroidVersionDetails(info: AndroidVersionInfo, onBack: () -> Unit) {
    BackHandler(enabled = true, onBack = onBack)
    Column(modifier = Modifier.padding(20.dp)) {
      Text(text = info.publicName, style = MaterialTheme.typography.h3)
      Text(text = "${info.codename} - API ${info.apiVersion}", style = MaterialTheme.typography.subtitle2)
      Text(
        modifier = Modifier.padding(top = 20.dp),
        text = info.details,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )

    }
  }

  @Composable
  private fun AndroidVersionInfoCard(info: AndroidVersionInfo, onClick: (AndroidVersionInfo) -> Unit) {
    Card(modifier = Modifier
      .fillMaxWidth()
      .defaultMinSize(minHeight = 120.dp)
      .clickable { onClick(info) }
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