package com.example.dripper.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.google.firebase.database.FirebaseDatabase

@Composable
fun MainScreen(
    database: FirebaseDatabase
) {
    val viewModel = MainScreenViewModel(database)
    viewModel.setPlants()

    Content(viewModel)
}

@Composable
private fun Content(
    viewModel: MainScreenViewModel
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        RoomInfo(
            roomTemperature = viewModel.room.temperature,
            roomHumidity = viewModel.room.humidity
        )
        Plants(
            plants = viewModel.plants,
            maxNumberOfPlants = viewModel.maxNumberOfPlants
        )
    }
}

@Composable
private fun RoomInfo (
    roomTemperature: Float,
    roomHumidity: Float
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(16.dp)
    ) {
        RoomInfoCard(text = "Room temperature:", value = "$roomTemperature\u00B0C", Modifier.fillMaxWidth(0.45f))
        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
        RoomInfoCard(text = "Room humidity:", value = "$roomHumidity%", Modifier.fillMaxWidth())
    }
}

@Composable
private fun RoomInfoCard(text: String, value: String, modifier: Modifier) {
    Card(
        modifier = modifier.fillMaxHeight()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                style = MaterialTheme.typography.headlineSmall,
                text = text)
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                text = value
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Plants(
    plants: List<Plant>,
    maxNumberOfPlants: Int
) {

    val pagerState = rememberPagerState(pageCount = { maxNumberOfPlants }, initialPage = 0)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    ) {
        HorizontalPager(
            state = pagerState,
        ) {
            val plant = plants.getOrNull(it)
            if (plant != null) {
                PlantInfoCard(
                    plantName = plant.name,
                    moisture = "${plant.moisture}%",
                    imgUrl = plant.imageURL
                )
            }
        }
    }
}

@Composable
private fun PlantInfoCard(
    plantName: String,
    moisture: String,
    imgUrl: String
) {

    Card(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Text(
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                style = MaterialTheme.typography.headlineSmall,
                text = plantName
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                text = moisture
            )
        }
    }
}

