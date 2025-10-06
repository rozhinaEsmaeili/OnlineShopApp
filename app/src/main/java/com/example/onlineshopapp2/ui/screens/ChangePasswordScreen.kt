package com.example.onlineshopapp2.ui.screens


import android.widget.Toast
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp2.db.viewmodels.UserEntityViewModel
import com.example.onlineshopapp2.models.customers.UserVM
import com.example.onlineshopapp2.viewmodels.customer.UserViewModel

@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var newPassword by remember { mutableStateOf(TextFieldValue()) }
    var newPasswordError by remember { mutableStateOf(false) }
    var repeatPassword by remember { mutableStateOf(TextFieldValue()) }
    var repeatPasswordError by remember { mutableStateOf(false) }
    var repeatPasswordNotMatchedError by remember { mutableStateOf(false) }
    var oldPassword by remember { mutableStateOf(TextFieldValue()) }
    var oldPasswordError by remember { mutableStateOf(false) }

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

                Text(
                    text = "Change Your Password",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        item {
            Spacer(Modifier.height(10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Spacer(Modifier.height(15.dp))
                OutlinedTextField(
                    value = oldPassword, onValueChange = {
                        oldPassword = it
                        oldPasswordError = false
                    },
                    label = { Text(text = " Enter Your Old Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (oldPasswordError) Icon(
                            Icons.Filled.Warning, contentDescription = "error",
                            tint = Color.Red
                        )
                    },
                    isError = oldPasswordError,
                    singleLine = true
                )
                if (oldPasswordError)
                    Text(
                        text = "Enter Your Old Password", fontSize = 14.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 12.dp)
                    )

                OutlinedTextField(
                    value = newPassword, onValueChange = {
                        newPassword = it
                        newPasswordError = false
                    },
                    label = { Text(text = "Enter Your New Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (newPasswordError) Icon(
                            imageVector = Icons.Filled.Warning, contentDescription = "error",
                            tint = Color.Red
                        )
                    },
                    singleLine = true,
                    isError = newPasswordError
                )
                if (newPasswordError) {
                    Text(
                        text = "Enter Your New Password",
                        fontSize = 14.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 12.dp)
                    )

                }

                OutlinedTextField(
                    value = repeatPassword, onValueChange = {
                        repeatPassword = it
                        repeatPasswordError = false
                        repeatPasswordNotMatchedError = false
                    },
                    label = { Text(text = "Repeat Your New Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (repeatPasswordError) Icon(
                            Icons.Filled.Warning, contentDescription = "error",
                            tint = Color.Red
                        )
                    },
                    isError = repeatPasswordError,
                    singleLine = true
                )
                if (repeatPasswordError)
                    Text(
                        text = "Repaet Your New Password", fontSize = 14.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                if (repeatPasswordNotMatchedError) {
                    Text(
                        text = "Your repeatPassword is Wrong", fontSize = 14.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
                Spacer(Modifier.height(50.dp))

                Button(
                    onClick = {
                        if (oldPassword.text.isEmpty()) oldPasswordError = true
                        if (newPassword.text.isEmpty()) newPasswordError = true
                        if (repeatPassword.text.isEmpty()) repeatPasswordError = true

                        if (oldPasswordError || newPasswordError || repeatPasswordError) {
                            return@Button
                        }
                        if (repeatPassword.text != newPassword.text) repeatPasswordNotMatchedError =
                            true

                        val userVm = UserVM(
                            oldPassword = oldPassword.text,
                            password = newPassword.text,
                            repeatPassword = repeatPassword.text,
                            token = userEntityViewModel.cuurentUser.value?.token,
                            id = userEntityViewModel.cuurentUser.value?.userId,
                            customerId = userEntityViewModel.cuurentUser.value?.customerId
                        )
                        userViewModel.changePassword(userVm) { response ->
                            if (response.status == "OK") {

                                Toast.makeText(
                                    context,
                                    "Your Password Changed!",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                navController.navigate("home")
                            }
                        }

                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, 0.dp)
                        .size(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text(
                        text = "Chnage Password",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }

        }
    }
}