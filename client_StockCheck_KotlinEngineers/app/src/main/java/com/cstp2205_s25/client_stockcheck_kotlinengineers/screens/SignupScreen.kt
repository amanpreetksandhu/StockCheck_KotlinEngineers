package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeTextField

@Composable
fun SignupScreen(
    onSignupClick: (email: String, employeeId: String, password: String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var employeeId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up")
        Spacer(modifier = Modifier.height(16.dp))

        ComposeTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        ComposeTextField(
            value = employeeId,
            onValueChange = { employeeId = it },
            label = "Employee ID",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        ComposeTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        ComposeButton(
            text = "Sign Up",
            onClick = {
                if (email.isBlank() || employeeId.isBlank() || password.isBlank()) {
                    errorMessage = "Please fill all fields"
                } else {
                    errorMessage = null
                    onSignupClick(email, employeeId, password)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        errorMessage?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}
