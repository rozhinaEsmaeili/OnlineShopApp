package com.example.onlineshopapp2.ui.components.sliders

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
import com.example.onlineshopapp2.models.site.Slider
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SliderItemView(slider: Slider) {
    Card(
        modifier = Modifier
            .width(320.dp)
            .height(240.dp)
            .padding(5.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        shape = RoundedCornerShape(20.dp),
        onClick = {/* TODO*/ }
    ) {
        Box() {
            GlideImage(
                imageModel = { slider.image },
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
                        text = slider.title!!, color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                    Text(
                        text = slider.subTitle!!, color = Color.LightGray,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}