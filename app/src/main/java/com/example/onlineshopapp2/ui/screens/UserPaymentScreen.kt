package com.example.onlineshopapp2.ui.screens

import android.content.Intent
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.MainActivity
import com.example.onlineshopapp2.db.viewmodels.BasketEntityViewModel
import com.example.onlineshopapp2.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp2.models.customers.UserVM
import com.example.onlineshopapp2.models.invoices.InvoiceItem
import com.example.onlineshopapp2.models.invoices.PaymentTransaction
import com.example.onlineshopapp2.ui.theme.Black
import com.example.onlineshopapp2.viewmodels.Transactions.TransactionViewModel
import com.example.onlineshopapp2.viewmodels.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserPaymentScreen(
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    mainActivity: MainActivity,
    userViewModel: UserViewModel = hiltViewModel(),
    transactionViewModel: TransactionViewModel = hiltViewModel()

) {
    val isLoggedIn by remember { mutableStateOf(userEntityViewModel.isLoggedIn()) }
    var currentUser by remember { mutableStateOf(userEntityViewModel.cuurentUser) }
    val context = LocalContext.current


    var firstName by remember { mutableStateOf(TextFieldValue((if (isLoggedIn) currentUser.value?.firstName!! else ""))) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf(TextFieldValue((if (isLoggedIn) currentUser.value?.lastName!! else ""))) }
    var lastNameError by remember { mutableStateOf(false) }
    var postalCode by remember { mutableStateOf(TextFieldValue((if (isLoggedIn) currentUser.value?.postalCode!! else ""))) }
    var postalCodeError by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf(TextFieldValue((if (isLoggedIn) currentUser.value?.username!! else ""))) }
    var userNameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf(TextFieldValue((if (isLoggedIn) currentUser.value?.phone!! else ""))) }
    var phoneError by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue((if (isLoggedIn) currentUser.value?.address!! else ""))) }
    var addressError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .padding(0.dp, 10.dp)
            .fillMaxSize()
    ) {

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
                Spacer(Modifier.width(5.dp))

                Text(
                    text = "Complete Your Information",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        item {

            Spacer(Modifier.height(15.dp))
            OutlinedTextField(
                value = userName, onValueChange = {
                    userName = it
                    userNameError = false
                },
                label = { Text(text = "UserName") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                enabled = !isLoggedIn,
                trailingIcon = {
                    if (userNameError) Icon(
                        Icons.Filled.Warning, contentDescription = "error",
                        tint = Color.Red
                    )
                },
                isError = userNameError,
                singleLine = true
            )
            if (userNameError) Text(
                text = "Enter Your UserName", fontSize = 14.sp,
                color = Color.Red,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(Modifier.height(5.dp))

            if (!isLoggedIn) {
                OutlinedTextField(
                    value = password, onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (passwordError) Icon(
                            imageVector = Icons.Filled.Warning, contentDescription = "error",
                            tint = Color.Red
                        )
                    },
                    singleLine = true,
                    isError = passwordError
                )
                if (passwordError) {
                    Text(
                        text = "Enter Your Password",
                        fontSize = 14.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
                Spacer(Modifier.height(5.dp))
            }

            OutlinedTextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                    firstNameError = false
                },
                label = { Text(text = "FirstName") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                trailingIcon = {
                    if (firstNameError) Icon(
                        Icons.Filled.Warning, contentDescription = "",
                        tint = Color.Red
                    )
                },
                isError = firstNameError,
                singleLine = true
            )
            if (firstNameError) {
                Text(
                    text = "Enter Your FirstName",
                    fontSize = 14.sp,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Spacer(Modifier.height(5.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                    lastNameError = false
                },
                label = { Text(text = "LastName") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                trailingIcon = {
                    if (lastNameError) Icon(
                        Icons.Filled.Warning, contentDescription = "",
                        tint = Color.Red
                    )
                }, isError = lastNameError,
                singleLine = true
            )
            if (lastNameError) {
                Text(
                    text = "Enter Your Password",
                    fontSize = 14.sp,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Spacer(Modifier.height(5.dp))

            OutlinedTextField(
                value = phone, onValueChange = {
                    phone = it
                    phoneError = false
                },
                label = { Text(text = "Phone") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                trailingIcon = {
                    if (phoneError) Icon(
                        Icons.Filled.Warning,
                        contentDescription = "error",
                        tint = Color.Red
                    )
                },
                singleLine = true,
                isError = phoneError
            )
            if (phoneError) {
                Text(
                    text = "Enter Your phone",
                    fontSize = 14.sp,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Spacer(Modifier.height(5.dp))

            OutlinedTextField(
                value = postalCode, onValueChange = {
                    postalCode = it
                    postalCodeError = false
                },
                label = { Text(text = "PostalCode") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                trailingIcon = {
                    if (postalCodeError) Icon(
                        Icons.Filled.Warning,
                        contentDescription = "error",
                        tint = Color.Red
                    )
                },
                isError = postalCodeError,
                singleLine = true
            )
            if (postalCodeError) {
                Text(
                    text = "Enter Your PostalCode",
                    fontSize = 14.sp,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Spacer(Modifier.height(5.dp))

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    addressError = false
                },
                label = { Text(text = "Address") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                trailingIcon = {
                    if (addressError) Icon(
                        Icons.Filled.Warning, contentDescription = "error",
                        tint = Color.Red
                    )
                },
                isError = addressError,
            )
            if (addressError) {
                Text(
                    text = "Enter Your Address",
                    fontSize = 14.sp,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Spacer(Modifier.height(50.dp))

            Column {
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Button(
                        onClick = {
                            if (userName.text.isEmpty()) userNameError = true
                            if (!isLoggedIn && password.text.isEmpty()) passwordError = true
                            if (firstName.text.isEmpty()) firstNameError = true
                            if (lastName.text.isEmpty()) lastNameError = true
                            if (phone.text.isEmpty()) phoneError = true
                            if (postalCode.text.isEmpty()) postalCodeError = true
                            if (address.text.isEmpty()) addressError = true
                            if (userNameError || passwordError || firstNameError || lastNameError || phoneError ||
                                postalCodeError || addressError
                            ) {
                                return@Button
                            }

                            val user = UserVM(
                                username = userName.text,
                                password = password.text,
                                firstName = firstName.text,
                                lastName = lastName.text,
                                address = address.text,
                                postalCode = postalCode.text,
                                id = if (isLoggedIn) currentUser.value?.userId else null,
                                customerId = if (isLoggedIn) currentUser.value?.customerId else null,
                                phone = phone.text,
                            )

                            val items = ArrayList<InvoiceItem>()

                            basketViewModel.dataListLive.value.forEach {
                                items.add(InvoiceItem.convertToInvoiceItems(it))
                            }

                            val request = PaymentTransaction(items = items, user = user)
                            isLoading = true
                            transactionViewModel.goToPayment(request) { response ->
                                if (response.status == "OK" && response.data!!.isNotEmpty()) {
                                    if (!isLoggedIn) {
                                        userViewModel.login(
                                            UserVM(
                                                username = userName.text,
                                                password = password.text
                                            )
                                        ) { userResponse ->
                                            if (userResponse.status == "OK") {
                                                val user = userResponse.data?.get(0)
                                                CoroutineScope(Dispatchers.IO).launch {
                                                    user?.let { userEntityViewModel.insert(it.convertToUserEntity()) }
                                                }
                                            }
                                        }
                                    }
                                    CoroutineScope(Dispatchers.IO).launch {
                                        basketViewModel.deleteAll()
                                    }
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        response.data!![0].toUri()
                                    )
                                    context.startActivity(intent)
                                } else response.message?.let {
                                    if (it.isNotEmpty()) {
                                        Toast.makeText(context, response.message, LENGTH_LONG)
                                            .show()
                                    }
                                }
                                isLoading = false
                            }
                        },
                        modifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp),
                        colors = ButtonDefaults.buttonColors(Black)
                    ) {
                        Text(
                            text = "Pay ",
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}


