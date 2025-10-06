package com.example.onlineshopapp2.ui.screens

import android.R
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp2.ui.components.BasketItemView
import com.example.onlineshopapp2.ui.components.utils.formatted

@Composable
fun BasketListScreen(navController: NavHostController, basketViewModel: BasketEntityViewModel) {

    var dataList by remember { mutableStateOf(basketViewModel.dataListLive) }

    var totalPriceLong: Long = 0

    for (item in dataList.value) {
        totalPriceLong += (item.quantity * item.price!!)
    }

    var totalPrice = remember { mutableLongStateOf(totalPriceLong) }


    LazyColumn {
        item {
            Row {
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
                Spacer(Modifier.width(10.dp))
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Shopping Cart",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(8.dp))
                    if (dataList.value.isNotEmpty()) {
                        Text(
                            text = "${dataList.value.size} Items",
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        if (dataList.value.isEmpty()) {
            item {
                Column(
                    modifier = Modifier.fillParentMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "your shopping cart is empty !",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(30.dp))
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart, "",
                        modifier = Modifier.size(180.dp),
                        tint = Color.DarkGray
                    )
                    Spacer(Modifier.height(15.dp))
                }
            }
        } else {

            items(dataList.value.size) { index ->
                Spacer(Modifier.height(15.dp))
                BasketItemView(
                    dataList.value[index],
                    basketViewModel,
                    totalPrice,
                    navController
                )
            }

            item {
                Column {
                    Spacer(Modifier.height(10.dp))

                    Row(modifier = Modifier.padding(12.dp, 0.dp)) {
                        Text(
                            text = "Total Price : ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "${formatted(totalPrice.longValue)} T",
                            fontSize = 19.sp,
                            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
                        )
                    }

                    Spacer(Modifier.height(28.dp))
                    Button(
                        onClick = {
                            navController.navigate("proccedToPayment")
                        },
                        modifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp),
                        colors = ButtonDefaults.buttonColors(Color.Gray)
                    ) {
                        Text(
                            text = "Procced To Payment ",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


