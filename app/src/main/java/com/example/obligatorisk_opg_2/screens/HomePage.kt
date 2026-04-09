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
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomePage(
    onNavigateToListPage: () -> Unit,
    onLogin: (email: String, password: String) -> Unit = { _, _ -> },
    onRegister: (email: String, password: String) -> Unit = { _, _ -> },
    user: String? = null,
    message: String = "",
    onLogOut: () -> Unit = {}
) {
    Scaffold { innerPadding ->
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }
        val auth = FirebaseAuth.getInstance()

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
                    text = "Login"
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.padding(16.dp),
                    label = { Text("Username") },
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
                            onLogin(email, password)
                            onNavigateToListPage()
//                            auth.createUserWithEmailAndPassword(email, password)
//                                .addOnCompleteListener { task ->
//                                    if (task.isSuccessful) {
//                                        message = "Sign up successful: ${auth.currentUser?.email ?: "unknown"}"
//                                        onNavigateToListPage()
//                                    } else {
//                                        message = "Sign up failed: ${task.exception?.localizedMessage ?: "unknown error"}"
//                                    }
//                                }
                        } else {
                            message = "Email and password cannot be empty"
                        }
                    }) {
                        Text(text = "Register", textAlign = TextAlign.Left)
                    }
                    Button(
                        onClick = {
                            if (email.isNotBlank() && password.isNotBlank()) {
                                onRegister(email, password)
                                onNavigateToListPage()
//                                auth.signInWithEmailAndPassword(email, password)
//                                    .addOnCompleteListener { task ->
//                                        if (task.isSuccessful) {
//                                            message = "Log in successful: ${auth.currentUser?.email ?: "unknown"}"
//                                            onNavigateToListPage()
//                                        } else {
//                                            message = "Log in failed: ${task.exception?.localizedMessage ?: "unknown error"}"
//                                        }
//                                    }
                            } else {
                                message = "Email and password cannot be empty"
                            }
                        },
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text("Log In")
                    }
                }
                Text(
                    text = message,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
