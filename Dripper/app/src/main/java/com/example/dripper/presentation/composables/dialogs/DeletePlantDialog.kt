package com.example.dripper.presentation.composables.dialogs

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
        }
    )

}
