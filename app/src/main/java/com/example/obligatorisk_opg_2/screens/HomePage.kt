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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomePage(
    onNavigateToListPage: () -> Unit,
) {
    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                text = "My Friendships",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            HorizontalDivider(thickness = 2.dp)

        }


        val auth = FirebaseAuth.getInstance()
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = 30.sp,
                text = "Login")
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.padding(16.dp),
                label = { Text("Username ") },
                singleLine = true
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.padding(16.dp),
                label = { Text("Password ") },
                singleLine = true
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 62.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    // TODO validate email and password
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                message =
                                    "Sign up successful: ${auth.currentUser?.email ?: "unknown"}"
                                    onNavigateToListPage()
                            } else {
                                message =
                                    "Sign up failed: ${task.exception?.localizedMessage ?: "unknown error"}"
                            }
                        }
                }) {
                    Text(text = "Register",
                        textAlign = TextAlign.Left)
                }
                Button(onClick = {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                message = "Log in successful: ${auth.currentUser?.email ?: "unknown"}"
                                onNavigateToListPage()
                            } else {
                                message = "Log in failed: ${task.exception?.localizedMessage ?: "unknown error"}"
                            }
                        }
                },
                    Modifier.padding(10.dp)
                ) {

                    Text("Log In")
                }
            }
            Text(message)
        }




            }

        }


