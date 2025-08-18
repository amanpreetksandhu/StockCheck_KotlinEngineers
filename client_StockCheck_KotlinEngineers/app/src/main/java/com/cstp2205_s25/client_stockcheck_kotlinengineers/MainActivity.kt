package com.cstp2205_s25.client_stockcheck_kotlinengineers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.NavSupport
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.UserProfileScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.services.SocketManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity( ) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SocketManager.init(this) // Doorway for the notification to work based on DB changes
        // Connection to the server for access to the DB

        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavSupport()

                }
            }
        }
    }
}


