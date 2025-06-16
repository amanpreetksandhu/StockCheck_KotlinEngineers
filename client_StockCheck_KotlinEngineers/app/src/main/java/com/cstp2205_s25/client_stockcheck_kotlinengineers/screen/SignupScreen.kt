package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cstp2205_s25.client_stockcheck_kotlinengineers.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeTextField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entitie.ApiService
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.ui.theme.scBlue
import com.cstp2205_s25.client_stockcheck_kotlinengineers.ui.theme.scGreen
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(
    onSignupSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    var email = authViewModel.email
    var employeeId = authViewModel.employeeId
    var password = authViewModel.password
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.stockcheck_wordmark),
            contentDescription = "StockCheck WorkMark",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(40.dp))

        ComposeTextField(
            value = email,
            onValueChange = { authViewModel.email = it },
            label = "Email",
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = scGreen,
                focusedLabelColor = scGreen,
                cursorColor = scGreen
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        ComposeTextField(
            value = employeeId,
            onValueChange = { authViewModel.employeeId = it },
            label = "Employee ID",
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = scGreen,
                focusedLabelColor = scGreen,
                cursorColor = scGreen
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        ComposeTextField(
            value = password,
            onValueChange = { authViewModel.password = it },
            label = "Password",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = scGreen,
                focusedLabelColor = scGreen,
                cursorColor = scGreen
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        ComposeButton(
            text = "Sign Up",
            onClick = {
                scope.launch {
                    try {
                        val success = ApiService.signup(email, employeeId, password)
                        errorMessage = if (success) {
                            onSignupSuccess()
                            "Signup successful!"
                        } else {
                            "Signup failed. Please try again."
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()  // You can also log this
                        errorMessage = "An error occurred: ${e.localizedMessage}"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = scGreen, // Your custom green
                contentColor = Color.White // White text
            )
        )
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Log In!",
                color = scBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable {
                        // Navigate to LoginScreen
                        onNavigateToLogin()
                    }
            )
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }


    }
}
