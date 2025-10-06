package com.example.onlineshopapp2.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp2.models.customers.UserVM
import com.example.onlineshopapp2.ui.theme.Black
import com.example.onlineshopapp2.viewmodels.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var userName by remember { mutableStateOf(TextFieldValue()) }
    var userNameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

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
                Spacer(Modifier.width(5.dp))
            }
        }

        item {
            Spacer(Modifier.height(20.dp))
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Let's Sign You In",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(20.dp))

                Text(
                    text = "You've been missed!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(30.dp))

                OutlinedTextField(
                    value = userName, onValueChange = {
                        userName = it
                        userNameError = false
                    },
                    label = { Text(text = "UserName") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(10.dp),
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
                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = password, onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(10.dp),
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
                Spacer(Modifier.height(40.dp))

                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Button(
                        onClick = {

                            if (password.text.isEmpty()) passwordError = true
                            if (userName.text.isEmpty()) userNameError = true
                            if (userNameError || passwordError) return@Button

                            isLoading = true
                            userViewModel.login(
                                UserVM(
                                    username = userName.text,
                                    password = password.text
                                )
                            ) { response ->
                                if (response.status == "OK") {
                                    val user = response.data?.get(0)
                                    CoroutineScope(Dispatchers.IO).launch {
                                        user?.let { userEntityViewModel.insert(it.convertToUserEntity()) }
                                    }
                                    Toast.makeText(
                                        context,
                                        "Welcome Back dear ${user?.firstName}",
                                        LENGTH_LONG
                                    ).show()
                                    navController.navigate("home")
                                }
                                isLoading = false
                            }
                        },
                        modifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.DarkGray)
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 18.sp,
                            color = Color.White
                        )

                    }
                }
            }

        }

    }
}