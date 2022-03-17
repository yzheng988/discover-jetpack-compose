package dev.goobar.hellocompose.androidversiondetails

import androidx.lifecycle.ViewModel

class AndroidVersionDetailsViewModel : ViewModel() {

  data class State(
    val title: String,
    val subtitle: String,
    val description: String
  )
}