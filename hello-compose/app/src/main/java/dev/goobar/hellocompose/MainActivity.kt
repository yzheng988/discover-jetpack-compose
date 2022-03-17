package dev.goobar.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import dev.goobar.hellocompose.androidversiondetails.AndroidVersionDetails
import dev.goobar.hellocompose.androidversiondetails.AndroidVersionDetailsViewModel
import dev.goobar.hellocompose.androidversionslist.AndroidVersionsList
import dev.goobar.hellocompose.androidversionslist.AndroidVersionsListViewModel
import dev.goobar.hellocompose.androidversionslist.AndroidVersionsListViewModel.Event.SortChanged
import dev.goobar.hellocompose.androidversionslist.Sort
import dev.goobar.hellocompose.androidversionslist.Sort.ASCENDING
import dev.goobar.hellocompose.androidversionslist.Sort.DESCENDING
import dev.goobar.hellocompose.design.HelloComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HelloComposeTheme() {
        MainActivityContent()
      }
    }
  }

  @OptIn(ExperimentalMaterialApi::class)
  @Composable
  private fun MainActivityContent() {

    var selectedItem by rememberSaveable { mutableStateOf<AndroidVersionInfo?>(null) }

    val versionsListViewModel by viewModels<AndroidVersionsListViewModel>()
    val versionsListState by versionsListViewModel.state.collectAsState()


    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
      bottomSheetState = BottomSheetState(Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
      scaffoldState = bottomSheetScaffoldState,
      topBar = {
        MainAppBar(
          selectedItem = selectedItem,
          onBackClick = { selectedItem = null },
          onSortClick = {
            coroutineScope.launch {
              if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                bottomSheetScaffoldState.bottomSheetState.expand()
              } else {
                bottomSheetScaffoldState.bottomSheetState.collapse()
              }
            }
          }
        )
      },
      sheetContent = { MainBottomSheetContent(
        onCloseClicked = {
          coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
        },
        onSortSelected = { selectedSort ->
          coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
            versionsListViewModel.onEvent(SortChanged(selectedSort))
            bottomSheetScaffoldState.snackbarHostState.showSnackbar(
              message = when (selectedSort) {
                ASCENDING -> "Showing oldest first"
                DESCENDING -> "Showing newest first"
              }
            )
          }
        }
      ) },
      sheetPeekHeight = 0.dp
    ) {
      when (val currentItem = selectedItem) {
        null -> {
          AndroidVersionsList(
            state = versionsListState,
            onClick = { clickedInfo -> selectedItem = clickedInfo }
          )
        }
        else -> {
          val viewModel = AndroidVersionDetailsViewModel(currentItem)
          AndroidVersionDetails(viewModel = viewModel, onBackClick = { selectedItem = null })
        }
      }
    }
  }

  @Composable
  private fun MainAppBar(
    selectedItem: AndroidVersionInfo?,
    onBackClick: () -> Unit,
    onSortClick: () -> Unit
  ) {
    TopAppBar(
      contentPadding = PaddingValues(horizontal = 20.dp),
    ) {
      when (selectedItem) {
        null -> {
          Row() {
            Text("Hello Jetpack Compose", modifier = Modifier.weight(1f))
            Icon(
              painter = painterResource(id = R.drawable.ic_sort),
              contentDescription = "Sort icon",
              modifier = Modifier.clickable { onSortClick() }
            )
          }
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

  @Composable
  private fun MainBottomSheetContent(
    onSortSelected: (Sort) -> Unit,
    onCloseClicked: () -> Unit
  ) {
    Column(modifier = Modifier
      .defaultMinSize(minHeight = 160.dp)
      .fillMaxWidth(1f)
      .padding(20.dp)) {
      Text(
        text = "Close",
        modifier = Modifier
          .align(Alignment.End)
          .clickable { onCloseClicked() },
        color = MaterialTheme.colors.error
      )
      Text(
        text = "Newest first",
        modifier = Modifier
          .padding(vertical = 8.dp)
          .clickable { onSortSelected(DESCENDING) }
      )
      Text(
        text = "Oldest first",
        modifier = Modifier
          .padding(vertical = 8.dp)
          .clickable { onSortSelected(ASCENDING) }
      )
    }
  }
}