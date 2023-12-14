package com.example.cs3200firebasestarter.ui.components

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cs3200firebasestarter.ui.models.Habit

@Composable
fun HabitListItem(habit: Habit) {
  Surface { habit.title?.let { Text(text = it) } }}
