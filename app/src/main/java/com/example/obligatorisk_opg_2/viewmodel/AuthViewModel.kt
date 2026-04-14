package com.example.obligatorisk_opg_2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    var user: FirebaseUser? by mutableStateOf(auth.currentUser)
    var message by mutableStateOf("")

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser
                    message = "Login successful"
                } else {
                    user = null
                    message = "Login failed: ${task.exception?.localizedMessage ?: "unknown error"}"
                }
            }
    }

    fun signOut() {
        auth.signOut()
        user = null
        message = "Logged out"
    }

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser
                    message = "Registration successful"
                } else {
                    user = null
                    message = "Registration failed: ${task.exception?.localizedMessage ?: "unknown error"}"
                }
            }
    }
}