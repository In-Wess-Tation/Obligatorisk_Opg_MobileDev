package com.example.obligatorisk_opg_2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomePage(
    onLogin: (email: String, password: String) -> Unit = { _, _ -> },
    onRegister: (email: String, password: String) -> Unit = { _, _ -> },
    message: String = ""
) {
    Scaffold { innerPadding ->
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var localError by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    text = "My Friendships",
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                HorizontalDivider(thickness = 2.dp)
            }

            // Login Form
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fontSize = 30.sp,
                    text = "Login / Register"
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.padding(16.dp),
                    label = { Text("Email") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.padding(16.dp),
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        if (email.isNotBlank() && password.isNotBlank()) {
                            onRegister(email, password)
                            localError = ""
                        } else {
                            localError = "Email and password cannot be empty"
                        }
                    }) {
                        Text("Register")
                    }
                    Button(
                        onClick = {
                            if (email.isNotBlank() && password.isNotBlank()) {
                                onLogin(email, password)
                                localError = ""
                            } else {
                                localError = "Email and password cannot be empty"
                            }
                        },
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text("Log In")
                    }
                }
                Text(
                    text = if (localError.isNotBlank()) localError else message,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
