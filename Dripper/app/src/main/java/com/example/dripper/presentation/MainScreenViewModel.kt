package com.example.dripper.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class Plant(
    var name: String = "",
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

    var isRaspberryRunning by mutableStateOf(true)
    var room by mutableStateOf(Room(0f, 0f))
        private set

    var selectedPlantIndex by mutableIntStateOf(0)
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

    fun updateSelectedButton(buttonIndex: Int) {
        selectedPlantIndex = buttonIndex
    }

    fun showAddDialog() {
        showAddDialog = true
    }

    fun hideAddDialog() {
        showAddDialog = false
        newPlantName = ""
    }

    fun showEditDialog() {
        showEditDialog = true
    }

    fun hideEditDialog() {
        showEditDialog = false
        newPlantName = ""
    }

    fun showDeleteDialog() {
        showDeleteDialog = true
    }

    fun hideDeleteDialog() {
        showDeleteDialog = false
    }

    fun addPlant() {
        //        TODO: add url
        val newPlant = Plant(
            name = newPlantName,
            targetMoisture = newPlantTargetMoisture.toFloat()
        )
        if (selectedPlantIndex == 1) {
            firstPlantRef.setValue(newPlant)
        } else {
            secondPlantRef.setValue(newPlant)
        }


    }

    fun changeSwitchStatus() {
        val switchRef = database.getReference("Switch")
        val switchValue = switchRef.get().result.value
        isRaspberryRunning = if (switchValue == "On") {
            switchRef.setValue("Off")
            false
        } else {
            switchRef.setValue("On")
            true
        }

    }

    fun editPlant() {
        var name: String
        var targetMoisture: Float
        var imageURL: String
        if (selectedPlantIndex == 1) {
            name = firstPlantRef.child("name").get().result.value.toString()
            targetMoisture = firstPlantRef.child("targetMoisture").get().result.value as Float
            imageURL = firstPlantRef.child("imageURL").get().result.value.toString()
        } else {
            name = secondPlantRef.child("name").get().result.value.toString()
            targetMoisture = secondPlantRef.child("targetMoisture").get().result.value as Float
            imageURL = secondPlantRef.child("imageURL").get().result.value.toString()
        }
        if (newPlantName.isNotEmpty()) {
            name = newPlantName
        }
        if (!newPlantTargetMoisture.toFloat().equals(targetMoisture)) {
            targetMoisture = newPlantTargetMoisture.toFloat()
        }

        firstPlantRef.setValue(Plant(name, targetMoisture))
//        TODO: change imageURL if need
    }

    fun deletePlant() {
        val emptyPlant = Plant()
        if (selectedPlantIndex == 0) {
            firstPlantRef.setValue(emptyPlant)
        } else {
            secondPlantRef.setValue(emptyPlant)
        }
    }
}