package com.example.onlineshopapp2.ui.components.products

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopapp2.viewmodels.products.ProductViewModel

@Composable
fun ProductFilterView(viewModel: ProductViewModel = hiltViewModel()) {
    var filter = listOf("All", "New", "Popular")
    var selectedFilter by remember { mutableIntStateOf(0) }

    LazyRow() {
        items(filter.size) { index ->
            Spacer(modifier = Modifier.width(12.dp))
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedFilter == index) Color.LightGray else
                        Color.Transparent
                ),
                onClick = {
                    selectedFilter = index
                    when (selectedFilter) {
                        0 -> {
                            viewModel.getProducts(0, 6) {}
                        }

                        1 -> {
                            viewModel.getNewProducts {}
                        }

                        2 -> {
                            viewModel.getPopularProducts { }
                        }
                    }
                },
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = filter[index],
                    fontSize = 16.sp,
                    color = if (isSystemInDarkTheme() && selectedFilter != index) Color.White else Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

}