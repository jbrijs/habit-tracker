package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.models.Habit
import com.example.cs3200firebasestarter.ui.repositories.HabitRepository

class HabitsScreenState {
  val _habits = mutableStateListOf<Habit>()
  val habits: List<Habit>
    get() = _habits
}

class HabitsViewModel(application: Application) : AndroidViewModel(application) {
  val uiState = HabitsScreenState()
  suspend fun getHabits(){
    val habits = HabitRepository.getHabits()
    uiState._habits.clear()
    uiState._habits.addAll(habits)
  }
  suspend fun toggleHabitCompletion(habit: Habit){
    val habitCopy = habit.copy(completed = !(habit.completed ?: false))
    uiState._habits[uiState._habits.indexOf(habit)] = habitCopy
    HabitRepository.updateHabit(habitCopy)
  }
}
