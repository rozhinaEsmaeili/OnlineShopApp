package com.example.onlineshopapp2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.R
import com.example.onlineshopapp2.db.models.BasketEntity
import com.example.onlineshopapp2.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp2.ui.components.utils.formatted
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun BasketItemView(
    basket: BasketEntity,
    basketViewModel: BasketEntityViewModel,
    totalPrice: MutableLongState,
    navController: NavHostController,
) {

    var quantity by remember { mutableIntStateOf(basket.quantity) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(130.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp),
                    clip = true
                ),
            shape = RoundedCornerShape(20.dp),
            onClick = {
                navController.navigate("showProduct/${basket.productId}")
            }

        ) {
            GlideImage(
                imageModel = { basket.image },
                failure = {
                    Text(text = "image request failed.")
                },
                loading = {
                    LoadingInColumn(modifier = Modifier.height(150.dp))
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(Modifier.width(15.dp))

        Column {
            basket.title?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "${formatted(basket.price)} T",
                fontSize = 17.sp,
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.height(12.dp))
            Row {
                Card(
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.size(25.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(
                            "#${basket.hexColor}".toColorInt()
                        )
                    )
                ) {}
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "${basket.size}",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }

            Spacer(Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.decrementQuantity(basket)
                        }
                        quantity--
                        totalPrice.longValue -= basket.price!!
                    },
                    modifier = Modifier.size(25.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.outline_remove_24),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
                    )
                }

                Spacer(Modifier.width(7.dp))

                Text(
                    text = "$quantity",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(Modifier.width(7.dp))

                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.incrementQuantity(basket)
                        }
                        quantity++
                        totalPrice.value += basket.price!!
                    },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add, contentDescription = "",
                    )
                }

                Spacer(Modifier.width(15.dp))

                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.delete(basket)
                        }
                    },
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete, "",
                        tint = Color.Red
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(25.dp))
}