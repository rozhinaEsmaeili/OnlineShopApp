package com.example.onlineshopapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopapp2.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp2.ui.screens.SplashScreen
import com.example.onlineshopapp2.ui.theme.OnlineShopApp2Theme
import com.example.onlineshopapp2.utils.ThisApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var isLoading by remember { mutableStateOf(false) }

            val userEntityViewModel = ViewModelProvider(this)[UserEntityViewModel::class.java]
            userEntityViewModel.getCurrentUser().observe(this) {
                isLoading = true
                userEntityViewModel.cuurentUser.value = it
            }

            OnlineShopApp2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (userEntityViewModel.cuurentUser.value != null)
                        ThisApp.token = userEntityViewModel.cuurentUser.value?.token!!
                    if (isLoading)
                        SplashScreen(
                            this,
                            modifier = Modifier.padding(innerPadding),
                            userEntityViewModel
                        )
                }
            }
        }
    }
}
