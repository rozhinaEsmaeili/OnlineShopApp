package com.example.onlineshopapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.onlineshopapp2.ui.screens.MainScreen
import com.example.onlineshopapp2.ui.theme.OnlineShopApp2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineShopApp2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InnerPadding(modifier = Modifier.padding(innerPadding))
                }
                MainScreen(this)
            }
        }
    }
}

@Composable
fun InnerPadding(modifier: Modifier) {

}

