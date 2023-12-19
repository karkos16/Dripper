package com.example.dripper.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dripper.presentation.MainScreenViewModel
import com.example.dripper.presentation.composables.cud.CUDPanelCard
import com.example.dripper.presentation.composables.plant.PlantsSwipeable
import com.example.dripper.presentation.composables.room.RoomInfo

@Composable
fun MainScreenContent(
    viewModel: MainScreenViewModel
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        RoomInfo(
            viewModel.room
        )
        PlantsSwipeable(
            plants = viewModel.plants,
            maxNumberOfPlants = viewModel.maxNumberOfPlants
        )
        CUDPanelCard(
            viewModel
        )
    }
}