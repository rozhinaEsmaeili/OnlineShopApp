package com.example.onlineshopapp2.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopapp2.MainActivity
import com.example.onlineshopapp2.SplashActivity
import com.example.onlineshopapp2.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp2.viewmodels.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    splashActivity: SplashActivity,
    modifier: Modifier,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {

    userViewModel.getUserInfo { response ->
        if (response.status != "OK") {
            if (response.message!!.lowercase().startsWith("http 417")) {
                CoroutineScope(Dispatchers.IO).launch {
                    userEntityViewModel.deleteAll()
                }
            }
        }
        splashActivity.startActivity(Intent(splashActivity, MainActivity::class.java))

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.ShoppingCart, "",
            modifier = Modifier.size(130.dp),
            tint = Color.DarkGray
        )
        Spacer(Modifier.height(30.dp))
        CircularProgressIndicator()
    }

}