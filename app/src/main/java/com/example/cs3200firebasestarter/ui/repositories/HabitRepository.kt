package com.example.cs3200firebasestarter.ui.repositories

import com.example.cs3200firebasestarter.ui.models.Habit
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object HabitRepository {

  val habitCache = mutableListOf<Habit>()
  private var cacheInitialized = false
  suspend fun createHabit(title: String, doIt: Boolean): Habit {
    val doc = Firebase.firestore.collection("habits").document()
    val habit =
        Habit(
            title = title,
            completed = false,
            avoid = doIt,
            userId = UserRepository.getCurrentUserId(),
            id = doc.id)
    doc.set(habit).await()
    habitCache.add(habit)
    return habit
  }

  suspend fun getHabits(): List<Habit> {
    if (!cacheInitialized) {
      val snapshot =
          Firebase.firestore
              .collection("habits")
              .whereEqualTo("userId", UserRepository.getCurrentUserId())
              .get()
              .await()
      habitCache.addAll(snapshot.toObjects())
      cacheInitialized = true
    }
    return habitCache
  }

  suspend fun updateHabit(habit: Habit) {
    Firebase.firestore.collection("habits").document(habit.id!!).set(habit).await()
    val oldHabitIndex = habitCache.indexOfFirst { it.id == habit.id }
    habitCache[oldHabitIndex] = habit
  }
}
