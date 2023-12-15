package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.FormField
import com.example.cs3200firebasestarter.ui.viewmodels.HabitModificationViewModel
import kotlinx.coroutines.launch

@Composable
fun HabitModificationScreen(navHostController: NavHostController, id: String?) {
  val viewModel: HabitModificationViewModel = viewModel()
  val scope = rememberCoroutineScope()
  val state = viewModel.uiState

  LaunchedEffect(true) { viewModel.setUpInitialState(id) }
  LaunchedEffect(state.saveSuccess) {
    println(id)
    if (state.saveSuccess) {
      navHostController.navigate("home")
    }
  }
  Column(modifier = Modifier.fillMaxHeight().padding(vertical = 100.dp)) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp), shadowElevation = 4.dp, color = Color.White
    ) {
          Column(
              modifier = Modifier.padding(12.dp),
          ) {
            Text(
                text = "Create Habit",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
            FormField(
                value = state.title,
                onValueChange = { state.title = it },
                placeholder = { Text(text = "Title") })
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(text = "Avoid this habit:")
              Checkbox(checked = state.avoid, onCheckedChange = { state.avoid = it })
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Divider()
            Spacer(modifier = Modifier.padding(6.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
              TextButton(onClick = { navHostController.popBackStack() }) { Text(text = "Cancel") }
              Button(onClick = { scope.launch { viewModel.saveHabit() } }, elevation = null) {
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
