package com.example.dripper.presentation.composables.plant

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dripper.presentation.Plant

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantsSwipeable(
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
                    plant
                )
            }
        }
    }
}