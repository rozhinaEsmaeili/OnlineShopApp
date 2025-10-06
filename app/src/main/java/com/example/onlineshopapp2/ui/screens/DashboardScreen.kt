package com.example.onlineshopapp2.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp2.ui.components.AdvancedButton
import com.example.onlineshopapp2.ui.theme.Blue
import com.example.onlineshopapp2.ui.theme.Green
import com.example.onlineshopapp2.ui.theme.Lemon
import com.example.onlineshopapp2.ui.theme.Red
import com.example.onlineshopapp2.utils.ThisApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun DashboardScreen(navController: NavHostController, userEntityViewModel: UserEntityViewModel) {

    val currentUser by remember { mutableStateOf(userEntityViewModel.cuurentUser) }

    Column(modifier = Modifier.padding(10.dp, 0.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(0.dp, 5.dp)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, "",
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(Modifier.width(8.dp))

            Text(
                text = "User Profile",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(Modifier.height(15.dp))

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Gray)
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "person",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(2.dp),
                    tint = Color.White
                )
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${currentUser.value?.firstName} ${currentUser.value?.lastName}",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "${currentUser.value?.username}", fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
                )
            }
            IconButton(onClick = {
                ThisApp.token = currentUser.value?.token!!
                navController.navigate("editProfile")
            }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "edit",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        Spacer(Modifier.height(50.dp))
        Text(
            text = "Account", fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            modifier = Modifier.padding(10.dp)
        )
        Spacer(Modifier.height(15.dp))

        LazyColumn {
            item {
                AdvancedButton(
                    title = "Invoices", "Show Your Invoices", Icons.Outlined.Star,
                    Blue
                ) {
                    ThisApp.userId = currentUser.value!!.userId!!
                    ThisApp.token = currentUser.value!!.token!!
                    navController.navigate("invoices")
                }
                AdvancedButton(
                    title = "Change Passsword", "Change Your Password", Icons.Outlined.Favorite,
                    Green
                ) {
                    navController.navigate("changePassword")
                }
                AdvancedButton(
                    title = "Help", "Help And FeedBack", Icons.Outlined.Info,
                    Lemon
                ) { }
                AdvancedButton(
                    title = "Logout", "Logout Your Account", Icons.AutoMirrored.Outlined.ExitToApp,
                    Red
                ) {
                    CoroutineScope(Dispatchers.IO).launch {
                        userEntityViewModel.delete(currentUser.value!!)
                    }
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }

                }
            }
        }
    }
}