package com.example.dripper.presentation.composables.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.dripper.presentation.MainScreenViewModel

@Composable
fun DeletePlantDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: MainScreenViewModel
) {
    AlertDialog(
        onDismissRequest =  onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        },
        text = {
            Column {
                Text(text = "Which plant delete?")
                RadioButtonsRow(
                    viewModel = viewModel,
                    firstButtonText = viewModel.plants[0].name,
                    secondButtonText = viewModel.plants[1].name
                )
            }
        }
    )

}
