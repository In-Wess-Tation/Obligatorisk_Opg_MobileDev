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
                    message = "Sign up successful: ${auth.currentUser?.email ?: "unknown"}"

                } else {
                    user = null
                    message = "Sign up failed: ${task.exception?.localizedMessage ?: "unknown error"}"
                }
            }
    }


        fun signOut() {
            user = null
            auth.signOut()
        }


        fun register(email: String, password: String) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        user = auth.currentUser
                        message = "Log in successful: ${auth.currentUser?.email ?: "unknown"}"
                    } else {
                        user = null
                        message = "Log in failed: ${task.exception?.localizedMessage ?: "unknown error"}"
                    }
                }
        }



}