package com.example.cs3200firebasestarter.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cs3200firebasestarter.ui.models.Habit
import com.example.cs3200firebasestarter.ui.viewmodels.HabitsViewModel

@Composable
fun HabitListItem(habit: Habit, toggle: (checked: Boolean) -> Unit = {}) {
  Surface(modifier = Modifier.fillMaxWidth().padding(16.dp), shadowElevation = 4.dp) {
    Column {
      Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        habit.title?.let {
          Text(
              text = it,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp))
        }
        IconButton(onClick = { /*TODO*/}) {
          Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit button")
        }
      }
      Divider()
      Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Mark as completed:", modifier = Modifier.padding(8.dp))
        habit.completed?.let {
          Checkbox(checked = it, onCheckedChange = {toggle(habit.completed)}, modifier = Modifier.padding(6.dp)) }
      }
    }
  }
}
