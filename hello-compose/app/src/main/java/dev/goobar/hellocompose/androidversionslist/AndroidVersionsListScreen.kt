package dev.goobar.hellocompose.androidversionslist

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.goobar.hellocompose.AndroidVersionInfo
import dev.goobar.hellocompose.AndroidVersionsRepository
import dev.goobar.hellocompose.R.drawable
import dev.goobar.hellocompose.androidversionslist.AndroidVersionsListViewModel.State.AndroidVersionViewItem
import dev.goobar.hellocompose.androidversionslist.Sort.ASCENDING
import dev.goobar.hellocompose.androidversionslist.Sort.DESCENDING

enum class Sort {
  ASCENDING, DESCENDING
}

@Composable
fun AndroidVersionsList(
  state: AndroidVersionsListViewModel.State,
  onClick: (AndroidVersionInfo) -> Unit
) {
  LazyColumn(
    contentPadding = PaddingValues(20.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    items(state.versionsList) { info ->
      AndroidVersionInfoCard(info, onClick)
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AndroidVersionInfoCard(viewItem: AndroidVersionViewItem, onClick: (AndroidVersionInfo) -> Unit) {
  val context = LocalContext.current

  Card(modifier = Modifier
    .fillMaxWidth()
    .defaultMinSize(minHeight = 120.dp)
    .combinedClickable(
      onLongClick = {
        Toast
          .makeText(context, "${viewItem.title} is my favorite!", Toast.LENGTH_SHORT)
          .show()
      },
      onClick = { onClick(viewItem.info) }
    )
  ) {
    Row(
      modifier = Modifier.padding(20.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        modifier = Modifier.size(64.dp),
        painter = painterResource(id = drawable.ic_android),
        contentDescription = "Android icon"
      )
      Column(modifier = Modifier.padding(start = 20.dp)) {
        Text(text = viewItem.title, style = MaterialTheme.typography.h4)
        Text(text = viewItem.subtitle)
        Text(
          modifier = Modifier.padding(top = 4.dp),
          text = viewItem.description,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}