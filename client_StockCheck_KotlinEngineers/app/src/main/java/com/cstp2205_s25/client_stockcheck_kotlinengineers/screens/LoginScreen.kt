package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.R
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeTextField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.ApiService
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.ui.theme.scGreen
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    var employeeId = authViewModel.employeeId
    var password = authViewModel.password
    var message by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope() // What does this do?

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = R.drawable.stockcheck_wordmark),
            contentDescription = "StockCheck WorkMark",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(40.dp))

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
            text = "Login",
            onClick = {
                scope.launch {
                    val success = ApiService.login(employeeId, password)
                    message = if (success) {
                        onLoginSuccess()
                        "Login successful!"
                    } else {
                        "Invalid credentials"
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
        Text(
            text = "I am a new user",
            fontSize = 14.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .clickable {
                    onNavigateToSignup()
                }
        )
        message?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}
