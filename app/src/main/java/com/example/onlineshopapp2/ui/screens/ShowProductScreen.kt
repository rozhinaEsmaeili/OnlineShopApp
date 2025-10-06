package com.example.onlineshopapp2.ui.screens

import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.ui.components.LoadingInRow
import com.example.onlineshopapp2.ui.theme.LowWhite
import com.example.onlineshopapp2.ui.theme.blackGradiant
import com.example.onlineshopapp2.viewmodels.products.ProductViewModel
import com.skydoves.landscapist.glide.GlideImage
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopapp2.db.models.BasketEntity
import com.example.onlineshopapp2.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp2.models.products.Product
import com.example.onlineshopapp2.ui.components.LoadingInColumn
import com.example.onlineshopapp2.ui.components.utils.formatted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ShowProductScreen(
    basketViewModel: BasketEntityViewModel,
    navController: NavHostController, productId: Long,
    viewModel: ProductViewModel = hiltViewModel()
) {
    var data by remember { mutableStateOf(viewModel.productDeatails) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableIntStateOf(0) }
    var selectedColor by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    viewModel.getProductById(productId) { response ->
        isLoading = false
        if (response.status == "OK") {
            if (response.data!!.isNotEmpty()) {
                viewModel.productDeatails.value = response.data!![0]
            } else {
                Toast.makeText(context, "Product not found", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }
        }
    }

    if (isLoading) {
        LoadingInColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    } else {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GlideImage(
                imageModel = { data.value?.image },
                failure = {
                    Text(text = "image request failed.")
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            blackGradiant
                        )
                    )
                )
        ) {}
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, "",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 13.dp, bottom = 15.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column() {
                data.value?.title?.let {
                    Text(
                        text = it, color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 28.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Sizes",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 25.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyRow {
                    data.value!!.sizes?.let {
                        items(it.size) { index ->
                            TextButton(
                                onClick = {
                                    selectedSize = index
                                },
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier.size(45.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedSize == index) LowWhite else Color.Transparent
                                )

                            ) {
                                Text(
                                    text = data.value!!.sizes!![index].title!!,
                                    fontSize = 16.sp,
                                    color = if (selectedSize == index) Color.Black else Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Colors",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(15.dp))

                LazyRow {
                    data.value!!.colors?.let {
                        items(it.size) { index ->
                            TextButton(
                                onClick = {
                                    selectedColor = index
                                },
                                shape = RoundedCornerShape(50.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        "#${data.value!!.colors!![index].hexValue}".toColorInt()
                                    )
                                ),
                                modifier = Modifier.size(43.dp)
                            ) {
                                if (selectedColor == index) {
                                    Icon(
                                        imageVector = Icons.Filled.Check, "",
                                        tint = if (data.value!!.colors!![index].hexValue!!.toLowerCase(
                                                Locale.current
                                            ) == "ffffff"
                                        ) Color.Black else Color.White
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "${formatted(data.value!!.price!!)} T", color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(28.dp))
                Button(
                    onClick = {
                        var basket = BasketEntity(
                            productId = productId,
                            colorId = data.value!!.colors!![selectedColor].id!!,
                            sizeId = data.value!!.sizes!![selectedSize].id!!,
                            quantity = 1,
                            image = data.value!!.image,
                            price = data.value!!.price,
                            title = data.value!!.title,
                            hexColor = data.value!!.colors!![selectedColor].hexValue!!,
                            size = data.value!!.sizes!![selectedSize].title
                        )
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.addToBasket(basket)
                        }
                        Toast.makeText(context, "product added to your basket", LENGTH_LONG).show()
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(end = 10.dp, bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LowWhite
                    )
                ) {
                    Text(
                        text = "Buy Now",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}



