package com.example.onlineshopapp2.ui.components.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlineshopapp2.models.products.Product
import com.example.onlineshopapp2.ui.components.utils.formatted
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductListItemView(product: Product, navController: NavController) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(5.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            navController.navigate("showProduct/${product.id}")
        }
    ) {
        Box() {
            GlideImage(
                imageModel = { product.image },
                failure = {
                    Text(text = "image request failed.")
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = product.title!!, color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = "${formatted(product.price)} T", color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}