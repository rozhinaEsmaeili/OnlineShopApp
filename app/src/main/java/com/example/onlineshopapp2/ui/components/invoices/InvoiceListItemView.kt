package com.example.onlineshopapp2.ui.components.invoices

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.models.invoices.Invoice
import com.example.onlineshopapp2.ui.components.AdvancedButton
import com.example.onlineshopapp2.ui.theme.Green
import com.example.onlineshopapp2.ui.theme.Red
import com.example.onlineshopapp2.utils.ThisApp

@Composable
fun InvoiceListItemView(invoice: Invoice, navController: NavHostController) {

    AdvancedButton(
        "${invoice.addDate}", "${invoice.status}",
        imageVector = if (invoice.status == "NotPayed") Icons.Filled.Info else Icons.Filled.Check,
        iconBackGroundColor = if (invoice.status == "NotPayed") Red else Green
    ) {
        navController.navigate("invoice/${invoice.id}")
    }

}