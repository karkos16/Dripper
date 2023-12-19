package com.example.dripper.presentation.composables.plant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PlantImage(imgUrl: String) {
    Box(modifier = Modifier
        .fillMaxHeight(0.6f)
        .fillMaxWidth()
        .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        var imageLoadedEnded by remember {
            mutableStateOf(false)
        }
        if (!imageLoadedEnded) {
            CircularProgressIndicator()
        }
        AsyncImage(
            model = imgUrl,
            contentDescription = "Plant image",
            onSuccess = {
                imageLoadedEnded = true
            },
            modifier = Modifier.clip(RoundedCornerShape(16.dp))
        )
    }
}