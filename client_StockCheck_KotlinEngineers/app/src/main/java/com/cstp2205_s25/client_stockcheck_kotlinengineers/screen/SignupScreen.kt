package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.ComposeTextField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.ApiService
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
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
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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
                 focusedBorderColor = Color(0xFF1D5C88),
                focusedLabelColor = Color(0xFF1D5C88),
               cursorColor = Color(0xFF1D5C88)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        ComposeTextField(
            value = employeeId,
            onValueChange = { authViewModel.employeeId = it },
            label = "Employee ID",
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1D5C88),
                focusedLabelColor = Color(0xFF1D5C88),
                cursorColor = Color(0xFF1D5C88)
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
                focusedBorderColor = Color(0xFF1D5C88),
                focusedLabelColor = Color(0xFF1D5C88),
                cursorColor = Color(0xFF1D5C88)

            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        ComposeButton(
            text = "Sign Up",
            onClick = {

                authViewModel.signup (context){
                    onSignupSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
               containerColor = Color(0xFF1D5C88)


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
                color = Color(0xFF1D5C88),
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


        authViewModel.errorMessage?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }


    }
}
