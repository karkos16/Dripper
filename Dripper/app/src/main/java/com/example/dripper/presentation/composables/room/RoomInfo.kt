package com.example.dripper.presentation.composables.room

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dripper.presentation.Room

@Composable
fun RoomInfo (
    roomInfo: Room
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        RoomInfoCard(text = "Room temperature:", value = "${roomInfo.temperature}\u00B0C", Modifier.fillMaxWidth(0.5f))
        RoomInfoCard(text = "Room humidity:", value = "${roomInfo.humidity}%", Modifier.fillMaxWidth())
    }
}