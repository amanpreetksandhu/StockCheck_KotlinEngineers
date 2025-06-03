package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.R
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeTextField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.ApiService
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignup: () -> Unit
) {
    var employeeId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        //verticalArrangement = Arrangement.Center,
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
            modifier = Modifier.fillMaxWidth()
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
