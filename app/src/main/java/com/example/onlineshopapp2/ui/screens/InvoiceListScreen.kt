package com.example.onlineshopapp2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.ui.components.LoadingInColumn
import com.example.onlineshopapp2.ui.components.invoices.InvoiceListItemView
import com.example.onlineshopapp2.viewmodels.invoices.InvoiceViewModel


@Composable
fun InvoiceListScreen(
    navController: NavHostController,
    invoiceViewModel: InvoiceViewModel = hiltViewModel()
) {
    var dataList by remember { mutableStateOf(invoiceViewModel.dataList) }
    var isLoading by remember { mutableStateOf(invoiceViewModel.isLoading) }

    if (isLoading.value) {
        LoadingInColumn(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
        )

    } else {
        LazyColumn {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "Invoices",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(Modifier.height(20.dp))
            }

            if (dataList.value.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Filled.Info, "",
                            modifier = Modifier.size(80.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = "You Dont Have Any Invoices !",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }
                }
            }

            items(invoiceViewModel.dataList.value.size) { index ->
                invoiceViewModel.onScrollPositionChange(index)
                if (index + 1 >= invoiceViewModel.pageIndex.intValue * invoiceViewModel.pageSize && !isLoading.value) {
                    invoiceViewModel.nextPage()
                }
                InvoiceListItemView(invoiceViewModel.dataList.value[index], navController)
            }

        }
    }

}