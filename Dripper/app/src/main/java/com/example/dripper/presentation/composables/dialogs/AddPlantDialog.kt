package com.example.dripper.presentation.composables.dialogs

import android.net.Uri
import android.widget.RadioGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.dripper.presentation.MainScreenViewModel

@Composable
fun AddPlantDialog(
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
        title = {
            Text("Add Plant")
        },
        text = {
            Column {
                Text(text = "Select plant:")
                RadioButtonsRow(viewModel, "First plant", "Second Plant")
                Text(
                    text = "Warning: this will overwrite the selected plant if it is already in use",
                    fontSize = 8.sp
                )
                OutlinedTextField(
                    label = { Text("Plant name") },
                    value = viewModel.newPlantName,
                    onValueChange = { viewModel.updateNewPlantName(it) }
                )
                OutlinedTextField(
                    label = { Text("Target moisture") },
                    value = viewModel.newPlantTargetMoisture.toString(),
                    onValueChange = { viewModel.updateNewPlantTargetMoisture(it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextButton(onClick = { launcher.launch("image/*") }) {
                    Text("Select image")
                }
            }

        }
    )
}

