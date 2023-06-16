package com.shaycormac.hammerapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.shaycormac.hammerapp.ui.screen.RootScreen
import com.shaycormac.hammerapp.ui.screen.viewmodel.MenuViewModel
import com.shaycormac.hammerapp.ui.theme.HammerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HammerAppTheme {
                RootScreen()
            }
        }
    }
}
