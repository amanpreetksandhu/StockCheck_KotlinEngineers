package com.cstp2205_s25.client_stockcheck_kotlinengineers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screens.SignupScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.ui.theme.StockCheck_KotlinEngineers_AppTheme
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    val currentScreen = remember { mutableStateOf("signup") } // or "login" or "home"
                    when (currentScreen.value) {
                        "signup" -> SignupScreen {
                                //On successful signup, navigate to login screen
                                currentScreen.value = "login"
                            }
                        "login" -> LoginScreen(
                                onLoginSuccess = {
                                    //On successful login, navigate to home or next screen
                                    currentScreen.value = "home"
                                },
                                onNavigateToSignup = {
                                    //User wants to go back to signup screen
                                    currentScreen.value = "signup"
                                }
                            )
                        "home" ->
                            Text(text = "HOme page")
                    }
                }
            }
        }
    }
}