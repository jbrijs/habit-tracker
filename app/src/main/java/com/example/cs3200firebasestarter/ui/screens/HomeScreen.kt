package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.HabitListItem
import com.example.cs3200firebasestarter.ui.viewmodels.HabitsViewModel

@Composable
fun HomeScreen(navHostController: NavHostController) {
  val viewModel: HabitsViewModel = viewModel()
  val state = viewModel.uiState

  LaunchedEffect(true) { viewModel.getHabits() }
  LazyColumn(modifier = Modifier.fillMaxHeight().padding(4.dp)) {
    items(state.habits, key = { it.id!! }) { HabitListItem(habit = it) }
  }
}
