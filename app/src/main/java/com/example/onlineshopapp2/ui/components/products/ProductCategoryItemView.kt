package com.example.onlineshopapp2.ui.components.products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.models.products.ProductCategory
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductCategoryItemView(category: ProductCategory, navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .padding(5.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            navController.navigate("products/${category.id}/${category.title}")
        }
    ) {
        Box() {
            GlideImage(
                imageModel = { category.image },
                failure = {
                    Text(text = "image request failed.")
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = category.title!!, color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )

                }
            }
        }
    }
}