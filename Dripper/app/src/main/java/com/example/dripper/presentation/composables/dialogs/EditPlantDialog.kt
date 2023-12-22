package com.example.dripper.presentation.composables.dialogs

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dripper.presentation.MainScreenViewModel

@Composable
fun EditPlantDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: MainScreenViewModel
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

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
                Text(text = "Which plant to edit?")
                RadioButtonsRow(
                    viewModel = viewModel,
                    firstButtonText = viewModel.plants[0].name,
                    secondButtonText = viewModel.plants[1].name
                )
                OutlinedTextField(
                    value = viewModel.newPlantName,
                    onValueChange = { viewModel.updateNewPlantName(it) },
                    placeholder = { Text(text = viewModel.plants[viewModel.selectedPlantIndex].name)}
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = viewModel.newPlantTargetMoisture,
                    onValueChange = { viewModel.updateNewPlantTargetMoisture(it) },
                    placeholder = { Text(text = viewModel.plants[viewModel.selectedPlantIndex].targetMoisture.toString()) }
                )
                TextButton(onClick = { launcher.launch("image/*") }) {
                    Text("Select image")
                }
            }
        }
    )
}