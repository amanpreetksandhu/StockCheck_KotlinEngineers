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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screens.LocationScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screens.NavSupport
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val locationViewModel: LocationViewModel = viewModel()

                    val authViewModel: AuthViewModel = viewModel()
                    NavSupport(authViewModel)
//                    LocationScreen(onNavigateToInventory = {navController.navigate(ScreenInventory.INVENTORIES.route)},
//                        onNavigateToAddLocation = {navController.navigate(ScreenInventory.ADDNEWLOCATION.route)},
//                        locationViewModel = locationViewModel)
                }
            }
        }

    }
}