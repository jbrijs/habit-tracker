package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.FormField
import com.example.cs3200firebasestarter.ui.navigation.Routes
import com.example.cs3200firebasestarter.ui.viewmodels.HabitModificationViewModel
import kotlinx.coroutines.launch

@Composable
fun HabitModificationScreen(navHostController: NavHostController, id: String?) {
  val viewModel: HabitModificationViewModel = viewModel()
  val scope = rememberCoroutineScope()
  val state = viewModel.uiState

//  LaunchedEffect(true) { viewModel.setUpInitialState(id) }
  LaunchedEffect(state.saveSuccess) {
    if (state.saveSuccess) {
      navHostController.navigate("home")
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
        Checkbox(checked = state.doIt, onCheckedChange = { state.doIt = it })
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
          TextButton(onClick = { navHostController.popBackStack() }) { Text(text = "Cancel") }
          Button(
              onClick = {
                scope.launch {
                  viewModel.saveHabit()
                }
              },
              elevation = null) {
                Text(text = "Save Habit")
              }
        }
        Text(
            text = state.errorMessage,
            style = TextStyle(color = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right)
      }
    }
  }
}
