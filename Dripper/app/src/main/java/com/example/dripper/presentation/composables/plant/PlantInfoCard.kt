package com.example.dripper.presentation.composables.plant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dripper.presentation.Plant
import com.example.dripper.presentation.composables.plant.PlantImage
import com.example.dripper.presentation.composables.plant.PlantInfo

@Composable
fun PlantInfoCard(
    plant: Plant
) {

    Card(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column {
            PlantImage(imgUrl = plant.imageURL)
            PlantInfo(plant = plant)
        }
    }
}

