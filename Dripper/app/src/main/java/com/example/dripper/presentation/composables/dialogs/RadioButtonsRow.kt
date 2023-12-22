package com.example.dripper.presentation.composables.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.dripper.presentation.MainScreenViewModel

@Composable
fun RadioButtonsRow(
    viewModel: MainScreenViewModel,
    firstButtonText : String,
    secondButtonText : String
    ) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = viewModel.selectedPlantIndex == 0,
            onClick = { viewModel.updateSelectedButton(0) }
        )
        Text(text = firstButtonText)
        RadioButton(
            selected = viewModel.selectedPlantIndex == 1,
            onClick = { viewModel.updateSelectedButton(1) }
        )
        Text(text = secondButtonText)
    }
}