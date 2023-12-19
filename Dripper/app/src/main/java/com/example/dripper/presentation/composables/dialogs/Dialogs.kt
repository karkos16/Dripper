package com.example.dripper.presentation.composables.dialogs

import androidx.compose.runtime.Composable
import com.example.dripper.presentation.MainScreenViewModel

@Composable
fun Dialogs(
    viewModel: MainScreenViewModel
) {
    if (viewModel.showAddDialog) {
        AddPlantDialog(
            onDismissRequest = { viewModel.hideAddDialog() },
            onConfirm = { viewModel.hideAddDialog() },
            viewModel
        )
    }
    if (viewModel.showEditDialog) {
        EditPlantDialog(
            onDismissRequest = { viewModel.hideEditDialog() },
            onConfirm = { viewModel.hideEditDialog() },
            viewModel
        )
    }
    if (viewModel.showDeleteDialog) {
        DeletePlantDialog(
            onDismissRequest = { viewModel.hideDeleteDialog() },
            onConfirm = { viewModel.hideDeleteDialog() },
            viewModel
        )
    }
}