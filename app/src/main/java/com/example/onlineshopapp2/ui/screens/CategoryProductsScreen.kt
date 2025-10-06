package com.example.onlineshopapp2.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.ui.components.LoadingInColumn
import com.example.onlineshopapp2.ui.components.LoadingInRow
import com.example.onlineshopapp2.ui.components.products.ProductListItemView
import com.example.onlineshopapp2.viewmodels.products.ProductsByCategoryIdViewModel

@Composable
fun CategoryProductsScreen(
    categoryId: Long,
    title: String,
    navController: NavHostController,
    viewModel: ProductsByCategoryIdViewModel = hiltViewModel()
) {
    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }


    LazyColumn(modifier = Modifier.padding(10.dp)) {

        item {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(Modifier.height(15.dp))
        }

        items(dataList.value.size) { index ->
            viewModel.onScrollPosition(index)
            if (index + 1 >= viewModel.pageIndex.intValue * viewModel.pageSize && !isLoading.value) {
                viewModel.nextPage()
            }
            ProductListItemView(dataList.value[index], navController)
        }

        if (isLoading.value) {
            item() {
                LoadingInColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }

    }
}
