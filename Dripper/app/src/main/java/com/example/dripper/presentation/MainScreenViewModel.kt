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
    val imageURL: String,
    val lastWatered: String
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

    var showAddDialog by mutableStateOf(false)
        private set

    var showEditDialog by mutableStateOf(false)
        private set

    var showDeleteDialog by mutableStateOf(false)
        private set

    fun setPlants() {
        plants.addAll(listOf(
            Plant(
                "Truskawka",
                70.1f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Strawberry_closeup.jpg/1280px-Strawberry_closeup.jpg",
                "2021-10-10"
            ),
            Plant(
                "Plant 2",
                50.2f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Strawberry_closeup.jpg/1280px-Strawberry_closeup.jpg",
                "2021-10-11"
            )
        ))
    }

    fun showAddDialog() {
        showAddDialog = true
    }

    fun hideAddDialog() {
        showAddDialog = false
    }

    fun showEditDialog() {
        showEditDialog = true
    }

    fun hideEditDialog() {
        showEditDialog = false
    }

    fun showDeleteDialog() {
        showDeleteDialog = true
    }

    fun hideDeleteDialog() {
        showDeleteDialog = false
    }
}