package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var email by  mutableStateOf("")
    var employeeId by  mutableStateOf("")
    var password by mutableStateOf("")

    // State for loading and error
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // LOGIN HANDLER
    fun login(onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = null

        // Fake delay or actual API call here
        viewModelScope.launch {
            val success = ApiService.login(employeeId,password)
            if (success){
                onSuccess()
            } else {
            errorMessage = "Invalid Credentials"
            }
            isLoading = false
        }
    }

    // SIGNUP HANDLER
    fun signup(onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            delay(1000)
            // Validate and call backend API
            if (employeeId.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                errorMessage = "Please fill all fields"
                isLoading = false
                return@launch // What does this do??
            }

            val success = ApiService.signup(employeeId,password,email)
            if (success) {
                onSuccess()
            } else {
                errorMessage = "Signup failed — try again"
            }

            isLoading = false
        }
    }
}