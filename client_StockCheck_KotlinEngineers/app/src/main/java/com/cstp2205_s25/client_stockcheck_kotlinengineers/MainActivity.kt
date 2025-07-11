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
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screens.LocationScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screens.NavSupport
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory

class MainActivity : ComponentActivity( ) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val inventoryViewModel: InventoryViewModel = viewModel()
                    val locationViewModel: LocationViewModel = viewModel()
                    val authViewModel: AuthViewModel = viewModel()


                   NavSupport(authViewModel)

                }
            }
        }

    }
}



