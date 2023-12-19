package com.example.dripper.presentation.composables.cud

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.dripper.presentation.MainScreenViewModel

@Composable
fun CUDPanel(
    viewModel: MainScreenViewModel
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CUDIcon(onClick = { viewModel.showAddDialog }, icon = Icons.Filled.AddCircle, description = "Add")
        CUDIcon(onClick = { viewModel.showEditDialog }, icon = Icons.Filled.Edit, description = "Edit")
        CUDIcon(onClick = { viewModel.showDeleteDialog }, icon = Icons.Filled.Delete, description = "Delete")
    }
}

@Composable
private fun CUDIcon(
    onClick : () -> Unit,
    icon : ImageVector,
    description: String,
    color: Color = MaterialTheme.colorScheme.background
) {
    IconButton(onClick = onClick, modifier = Modifier.background(
        color = color,
        shape = RoundedCornerShape(16.dp)
    )) {
        Icon(imageVector = icon, contentDescription = description)
    }
}