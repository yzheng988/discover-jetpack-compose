package dev.goobar.hellocompose.androidversiondetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.goobar.hellocompose.AndroidVersionInfo

@Composable
fun AndroidVersionDetails(info: AndroidVersionInfo, onBackClick: () -> Unit) {
  BackHandler(enabled = true, onBack = onBackClick)
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