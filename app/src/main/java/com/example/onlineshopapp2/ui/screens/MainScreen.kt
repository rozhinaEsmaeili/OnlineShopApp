package com.example.onlineshopapp2.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.onlineshopapp2.MainActivity
import com.example.onlineshopapp2.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp2.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp2.ui.components.TopAppView
import com.example.onlineshopapp2.utils.ThisApp

@Composable
fun MainScreen(mainActivity: MainActivity) {

    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    var showHomeButton by remember { mutableStateOf(false) }

    val basketViewModel =
        ViewModelProvider(mainActivity)[BasketEntityViewModel::class.java]

    val userEntityViewModel = ViewModelProvider(mainActivity)[UserEntityViewModel::class.java]

    basketViewModel.getBasketListLive().observe(mainActivity) {
        basketViewModel.dataListLive.value = it
    }

    userEntityViewModel.getCurrentUser().observe(mainActivity) {
        userEntityViewModel.cuurentUser.value = it
    }

    Scaffold(
        topBar = {
            if (!fullScreen) TopAppView(
                navController,
                basketViewModel,
                userEntityViewModel,
                showHomeButton
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(it)
        ) {
            composable("home") {
                fullScreen = false
                showHomeButton = false
                HomeScreen(navController)
            }
            composable("basket") {
                fullScreen = true
                showHomeButton = false
                BasketListScreen(
                    navController,
                    basketViewModel
                )
            }
            composable("proccedToPayment") {
                fullScreen = true
                showHomeButton = false
                UserPaymentScreen(
                    navController,
                    basketViewModel,
                    userEntityViewModel,
                    mainActivity
                )

            }
            composable(
                "products/{categoryId}/{title}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.LongType },
                    navArgument("title") { type = NavType.StringType }
                )
            ) { backStack ->
                fullScreen = false
                showHomeButton = false
                var categoryId = backStack.arguments?.getLong("categoryId")
                var title = backStack.arguments?.getString("title")
                ThisApp.categoryId = categoryId!!
                CategoryProductsScreen(categoryId, title!!, navController)
            }
            composable(
                "showProduct/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) { backStack ->
                fullScreen = true
                showHomeButton = false
                backStack.arguments?.getLong("productId").let { it ->
                    ShowProductScreen(basketViewModel = basketViewModel, navController, it!!)
                }

            }
            composable(
                "invoice/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType }),
                deepLinks = listOf(navDeepLink { uriPattern = "app://onlineshopholosen.ir/{id}" })
            ) { backStackEntry ->
                fullScreen = false
                showHomeButton = true
                if (userEntityViewModel.cuurentUser.value != null) {
                    ThisApp.invoiceId = backStackEntry.arguments?.getLong("id")!!
                    ThisApp.token = userEntityViewModel.cuurentUser.value?.token!!
                }
                InvoiceScreen(navController, backStackEntry.arguments?.getLong("id"))
            }
            composable("login") {
                fullScreen = true
                showHomeButton = false
                LoginScreen(navController, userEntityViewModel)
            }
            composable("dashboard") {
                fullScreen = true
                showHomeButton = false
                DashboardScreen(navController, userEntityViewModel)
            }
            composable("invoices") {
                fullScreen = true
                showHomeButton = false
                InvoiceListScreen(navController)
            }
            composable("editProfile") {
                fullScreen = true
                showHomeButton = false
                EditProfileScreen(navController, userEntityViewModel)
            }
            composable("changePassword") {
                fullScreen = true
                showHomeButton = false
                ChangePasswordScreen(navController, userEntityViewModel)
            }

        }
    }
}