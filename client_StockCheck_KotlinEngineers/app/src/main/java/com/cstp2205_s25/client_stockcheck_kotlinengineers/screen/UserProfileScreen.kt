package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.BlackText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun UserProfileScreen(
    authViewModel: AuthViewModel,
    onNavigateToLoginPage: () -> Unit,
){

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        authViewModel.loadCurrentUserEmail(context)
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp),
                tint = Color.Gray
            )
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            BlackText(
                text = authViewModel.email
            )

            Button(
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
        }

}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    val fakeAuthViewModel =  remember { AuthViewModel() }

    val fakeNavigate: () -> Unit = {}

    UserProfileScreen(
        authViewModel = fakeAuthViewModel,
        onNavigateToLoginPage = fakeNavigate
    )
}

