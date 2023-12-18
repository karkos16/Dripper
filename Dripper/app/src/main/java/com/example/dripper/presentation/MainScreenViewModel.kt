package com.example.dripper.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase

data class Plant(
    val name: String,
    val moisture: Float,
    val imageURL: String
)

data class Room(
    val temperature: Float,
    val humidity: Float
)

class MainScreenViewModel(
    private val database: FirebaseDatabase
): ViewModel() {

    var room by mutableStateOf(Room(0f, 0f))
        private set

    var plants = mutableStateListOf<Plant>()
        private set

    val maxNumberOfPlants = 2

    fun setPlants() {
        plants.addAll(listOf(
            Plant("Plant 1", 70.1f, "https://images.unsplash.com/photo-1612837017391-8b9b6b7b9b0f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGxhbnR8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80"),
            Plant("Plant 2", 50.2f, "https://images.unsplash.com/photo-1612837017391-8b9b6b7b9b0f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGxhbnR8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80")
        ))
    }
}