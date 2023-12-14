package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.FormField
import com.example.cs3200firebasestarter.ui.viewmodels.HabitModificationViewModel

@Composable
fun HabitModificationScreen(navHostController: NavHostController, id: String?) {
  val viewModel: HabitModificationViewModel = viewModel()
  val scope = rememberCoroutineScope()
  val state = viewModel.uiState

  LaunchedEffect(true) { viewModel.setUpInitialState(id) }
  LaunchedEffect(state.saveSuccess) {
    if (state.saveSuccess) {
      navHostController.navigate("habitsscreen")
    }
  }
  Column {
    Surface {
      Column {
        Text(text = "Create Habit")
        FormField(
            value = state.title,
            onValueChange = { state.title = it },
            placeholder = { Text(text = "Title") })
        Checkbox(checked = state.doIt, onCheckedChange = {state.doIt = it})
      }
    }
  }
}
