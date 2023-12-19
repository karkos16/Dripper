package com.example.dripper.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dripper.presentation.composables.MainScreenContent
import com.example.dripper.presentation.composables.dialogs.AddPlantDialog
import com.example.dripper.presentation.composables.dialogs.Dialogs
import com.google.firebase.database.FirebaseDatabase

@Composable
fun MainScreen(
    database: FirebaseDatabase
) {
    val viewModel = MainScreenViewModel(database)

    MainScreenContent(viewModel)
    Dialogs(viewModel)
}



