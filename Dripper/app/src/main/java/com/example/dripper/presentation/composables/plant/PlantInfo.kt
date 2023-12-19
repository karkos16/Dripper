package com.example.dripper.presentation.composables.plant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dripper.presentation.Plant

@Composable
fun PlantInfo(plant: Plant) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    style = MaterialTheme.typography.headlineSmall,
                    text = plant.name)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Last watered:")
                Text(text = plant.lastWatered)
            }
            Column {
                Text(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                    text = plant.currentMoisture.toString() + "%"
                )
                Text(text = "Moisture")
                Text(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                    text = plant.targetMoisture.toString() + "%"
                )
                Text(text = "Target moisture")
            }
        }
    }
}