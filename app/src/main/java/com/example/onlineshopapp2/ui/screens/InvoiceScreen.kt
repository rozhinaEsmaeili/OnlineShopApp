package com.example.onlineshopapp2.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.onlineshopapp2.ui.components.AdvancedButton
import com.example.onlineshopapp2.ui.components.LoadingInColumn
import com.example.onlineshopapp2.ui.theme.Blue
import com.example.onlineshopapp2.ui.theme.Green
import com.example.onlineshopapp2.ui.theme.Lemon
import com.example.onlineshopapp2.ui.theme.Red
import com.example.onlineshopapp2.viewmodels.invoices.InvoiceByIdViewModel

@Composable
fun InvoiceScreen(
    navController: NavHostController, id: Long?,
    viewModel: InvoiceByIdViewModel = hiltViewModel()
) {

    var invoice by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value || invoice.value == null) {
        LoadingInColumn(
            modifier = Modifier
                .fillMaxSize()
        )
    } else {
        Column() {

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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.size(100.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = CardDefaults.cardColors(if (invoice.value?.status == "NotPayed") Red else Green)
                ) {
                    Icon(
                        if (invoice.value?.status == "NotPayed") Icons.Filled.Info else Icons.Filled.Check,
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(7.dp),
                        tint = Color.White
                    )
                }
            }
            Column(modifier = Modifier.padding(17.dp, 10.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Status : ${invoice.value?.status}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Add Date : ${invoice.value?.addDate}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                if (invoice.value!!.status == "Payed") {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Payment Date :",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "${invoice.value?.paymentDate}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(modifier = Modifier.padding(2.dp)) {

                items(invoice.value?.items!!.size) { index ->
                    AdvancedButton(
                        "${invoice.value!!.items!![index].product?.title}",
                        subTitle = "${invoice.value!!.items!![index].unitPrice}",
                        imageVector = Icons.AutoMirrored.Filled.List,
                        iconBackGroundColor = Blue
                    ) {
                        navController.navigate("showProduct/${invoice.value?.items!![index].product?.id}")
                    }
                }
            }
        }
    }

}