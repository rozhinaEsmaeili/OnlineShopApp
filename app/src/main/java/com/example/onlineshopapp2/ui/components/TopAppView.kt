package com.example.onlineshopapp2.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp2.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp2.ui.theme.MeloRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppView(
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    showHomeButton: Boolean
) {

    val animatedVisibleState =
        remember { MutableTransitionState(false) }.apply { targetState = true }

    TopAppBar(
        title = {
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(animationSpec = tween(300))
                        + fadeIn(animationSpec = tween(300)),
                exit = fadeOut()
            ) {
                Text(
                    text = "Online Shop", fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {

            if (showHomeButton) {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(animationSpec = tween(300))
                            + fadeIn(animationSpec = tween(300)),
                    exit = fadeOut()
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate("home")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home, contentDescription = "home",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(animationSpec = tween(500, 600))
                        + fadeIn(animationSpec = tween(500, 600)),
                exit = fadeOut()
            ) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    IconButton(
                        onClick = {
                            navController.navigate("basket")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart, contentDescription = "cart",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    if (basketViewModel.dataListLive.value.isNotEmpty()) {

                        Card(
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier.size(22.dp),
                            colors = CardDefaults.cardColors(containerColor = MeloRed)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${basketViewModel.dataListLive.value.size}",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(animationSpec = tween(500,900))
                        + fadeIn(animationSpec = tween(500,900)),
                exit = fadeOut()
            ) {
                IconButton(onClick = {
                    if (!userEntityViewModel.isLoggedIn()) {
                        navController.navigate("login")
                    } else {
                        navController.navigate("dashboard")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Person, contentDescription = "person",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    )
}