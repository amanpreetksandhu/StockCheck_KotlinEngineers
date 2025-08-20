package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.ApiService
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.TokenManager


class AuthViewModel: ViewModel() {

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

            if (employeeId.isEmpty() || password.isEmpty() || email.isEmpty()) {
                errorMessage = "Please fill all fields"
                isLoading = false
                return@launch // to exit the launch block only not the whole signup fun.

            }

            val success = ApiService.signup(email, employeeId, password)

            if (success) {
                onSuccess()
            } else {
                errorMessage = "Signup failed — try again"
            }

            isLoading = false
        }
    }

    fun logout(context: Context, onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            TokenManager.clearToken(context)
            email = ""
            employeeId = ""
            password = ""
            errorMessage = null
            isLoading = false
            onLoggedOut()
        }
    }

}