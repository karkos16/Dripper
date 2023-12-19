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
            selected = viewModel.selectedButton == firstButtonText,
            onClick = { viewModel.updateSelectedButton(firstButtonText) }
        )
        Text(text = firstButtonText)
        RadioButton(
            selected = viewModel.selectedButton == secondButtonText,
            onClick = { viewModel.updateSelectedButton(secondButtonText) }
        )
        Text(text = secondButtonText)
    }
}