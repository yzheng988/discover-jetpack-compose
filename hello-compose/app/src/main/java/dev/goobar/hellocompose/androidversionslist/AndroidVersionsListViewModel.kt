package dev.goobar.hellocompose.androidversionslist

import androidx.lifecycle.ViewModel

class AndroidVersionsListViewModel : ViewModel() {

  data class State(
    val versionsList: List<AndroidVersionViewItem>
  ) {
    data class AndroidVersionViewItem(
      val title: String,
      val subtitle: String,
      val description: String
    )
  }
}