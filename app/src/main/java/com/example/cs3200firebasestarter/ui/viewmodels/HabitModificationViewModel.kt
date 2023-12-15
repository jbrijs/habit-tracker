package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.repositories.HabitRepository

class HabitModificationScreenState {
  var title by mutableStateOf("")
  var completed by mutableStateOf(false)
  var avoid by mutableStateOf(false)
  var titleError by mutableStateOf(false)
  var errorMessage by mutableStateOf("")
  var saveSuccess by mutableStateOf(false)
}

class HabitModificationViewModel(application: Application) : AndroidViewModel(application) {
  val uiState = HabitModificationScreenState()
  var id: String? = null

  suspend fun setUpInitialState(id: String?) {
    if (id == null || id == "new") return
    this.id = id
    val habit = HabitRepository.getHabits().find{it.id == id} ?: return
    uiState.title = habit.title ?: ""
    uiState.completed = habit.completed ?: false
    uiState.avoid = habit.avoid ?: false
  }
  suspend fun saveHabit() {
    uiState.errorMessage = ""
    uiState.titleError = false

    if (uiState.title.isEmpty()) {
      uiState.titleError = true
      uiState.errorMessage = "Title cannot be blank"
      return
    }

    if (id == null) {
      HabitRepository.createHabit(uiState.title, uiState.avoid)
    } else {
      val habit = HabitRepository.getHabits().find { it.id == id }
      if (habit != null) {
        HabitRepository.updateHabit(
            habit.copy(title = uiState.title, completed = uiState.completed, avoid = uiState.avoid))
      }
    }
    uiState.saveSuccess = true
  }
}
