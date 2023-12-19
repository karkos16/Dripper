package com.example.dripper.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class Plant(
    val name: String = "",
    val currentMoisture: Float = 0f,
    val targetMoisture: Float = 0f,
    val imageURL: String = "",
    val lastWatered: String = ""
)

data class Room(
    val temperature: Float = 0f,
    val humidity: Float = 0f
)

class MainScreenViewModel(
    private val database: FirebaseDatabase
): ViewModel() {

    private val roomInfoRef = database.getReference("roomInfo")
    private val firstPlantRef = database.getReference("plants").child("plant1")
    private val secondPlantRef = database.getReference("plants").child("plant2")
    var room by mutableStateOf(Room(0f, 0f))
        private set

    var selectedButton by mutableStateOf("")
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

    var newPlantName by mutableStateOf("")
        private set

    var newPlantTargetMoisture by mutableStateOf("")
        private set

    fun updateNewPlantName(name: String) {
        newPlantName = name
    }

    fun updateNewPlantTargetMoisture(moisture: String) {
        newPlantTargetMoisture = moisture
    }

    private val roomValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val roomSnapshot = snapshot.getValue(Room::class.java)
            room = roomSnapshot ?: Room(0f, 0f)
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d("MainScreenViewModel", "onCancelled: " + error.message)
        }
    }

    private val firstPlantValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val plantSnapshot = snapshot.getValue(Plant::class.java)
            if (plantSnapshot != null) {
                plants[0] = plantSnapshot
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d("MainScreenViewModel", "onCancelled: " + error.message)
        }
    }

    private val secondPlantValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val plantSnapshot = snapshot.getValue(Plant::class.java)
            if (plantSnapshot != null) {
                plants[1] = plantSnapshot
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d("MainScreenViewModel", "onCancelled: " + error.message)
        }
    }

    init {
        plants.add(Plant("Plant 1", 0f, 0f, "", ""))
        plants.add(Plant("Plant 1", 0f, 0f, "", ""))
        roomInfoRef.addValueEventListener(roomValueEventListener)
        firstPlantRef.addValueEventListener(firstPlantValueEventListener)
        secondPlantRef.addValueEventListener(secondPlantValueEventListener)
    }

    override fun onCleared() {
        super.onCleared()
        roomInfoRef.removeEventListener(roomValueEventListener)
        firstPlantRef.removeEventListener(firstPlantValueEventListener)
        secondPlantRef.removeEventListener(secondPlantValueEventListener)
    }

    fun updateSelectedButton(buttonName: String) {
        selectedButton = buttonName
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