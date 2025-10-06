package com.example.onlineshopapp2.ui.components.products

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.ui.components.LoadingInRow
import com.example.onlineshopapp2.viewmodels.products.ProductCategoryViewModel

@Composable
fun ProductCategoryListView(
    navController: NavHostController,
    viewModel: ProductCategoryViewModel = hiltViewModel(),
) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value) {
        LoadingInRow(
            modifier = Modifier
                .width(150.dp)
                .height(200.dp), 4
        )
    } else {
        LazyRow {
            items(dataList.value.size) { index ->
                ProductCategoryItemView(dataList.value[index], navController)
            }
        }
    }
}