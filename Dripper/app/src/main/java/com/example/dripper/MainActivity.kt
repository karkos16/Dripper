package com.example.dripper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dripper.presentation.MainScreen
import com.example.dripper.ui.theme.DripperTheme
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Firebase.database("https://dripper-48098-default-rtdb.europe-west1.firebasedatabase.app/")

        setContent {
            DripperTheme {
                MainScreen(database)
            }
        }
    }
}
