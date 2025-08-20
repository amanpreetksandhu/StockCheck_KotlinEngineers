package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.BlackText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Divider
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.GrayText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun UserProfileScreen(
    authViewModel: AuthViewModel,
    onNavigateToLoginPage: () -> Unit,
){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp),
                tint = Color.Gray
            )

            Spacer(Modifier.height(24.dp))

            BlackText(
                text = "Employee ID: ${authViewModel.employeeId}"
            )

            Divider()

            Spacer(Modifier.height(32.dp))



            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1D5C88)
                ),
                onClick = {
                    coroutineScope.launch {
                        authViewModel.logout(context) {
                            onNavigateToLoginPage()
                        }
                    }
                }


            ) {
                Text("Logout", color = Color.White)
            }


            Spacer(Modifier.height(80.dp))

            Text(
                text = "Contact Support",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://yourapp.com/privacy"))
                        context.startActivity(intent)
                    },
                color = Color(0xFF1D5C88),
                textDecoration = TextDecoration.Underline
            )

            Text(
                text = "Privacy Policy",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://yourapp.com/privacy"))
                        context.startActivity(intent)
                    },
                color = Color(0xFF1D5C88),
                textDecoration = TextDecoration.Underline
            )

            Text(
                text = "Terms of Service",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://yourapp.com/terms"))
                        context.startActivity(intent)
                    },
                color = Color(0xFF1D5C88),
                textDecoration = TextDecoration.Underline
            )


            Spacer(Modifier.height(200.dp))

            GrayText(text = "App version: 1.0.0")

        }

}