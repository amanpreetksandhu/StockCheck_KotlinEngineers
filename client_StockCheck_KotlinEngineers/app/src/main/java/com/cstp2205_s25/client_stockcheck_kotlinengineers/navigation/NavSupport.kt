package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.InventoryScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.AddNewLocationScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.EditLocationScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.LocationDetailsScreen

@Composable
fun NavSupport(vm: AuthViewModel) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val locationViewModel: LocationViewModel = viewModel()

    NavHost(navController = navController, startDestination = ScreenInventory.LOGIN.route) {

        // LOGIN ROUTES LOGIC----------------------------------------------\
        composable(ScreenInventory.LOGIN.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(ScreenInventory.INVENTORIES.route) {
                        popUpTo(ScreenInventory.LOGIN.route) { inclusive = true }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate(ScreenInventory.SIGNUP.route)
                },
                authViewModel = authViewModel
            )
        }

        // SIGNUP ROUTES LOGIC ---------------------------------------\
        composable(ScreenInventory.SIGNUP.route) {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate(ScreenInventory.LOCATIONS.route) {
                        popUpTo(ScreenInventory.SIGNUP.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }
        //LOCATIONS SCREEN---------------------------------------------\
        composable(ScreenInventory.LOCATIONS.route) {
            LocationScreen(
                onNavigateToInventory = { navController.navigate(ScreenInventory.INVENTORIES.route) },
                onNavigateToAddLocation = { navController.navigate(ScreenInventory.ADDNEWLOCATION.route) },
                onNavigateToEditLocation = { navController.navigate(ScreenInventory.EDITLOCATION.route) },
                locationViewModel = locationViewModel,
                onNavigateToLocationDetailsPage = { navController.navigate(ScreenInventory.LOCATIONDETAILS) }
            )

        }
        //INVENTORY SCREEN------------------------------------------------\
        composable(ScreenInventory.INVENTORIES.route) {
            InventoryScreen(onNavigateToLocation = { navController.navigate(ScreenInventory.LOCATIONS.route) })
        }

        //ADD NEW LOCATION SCREEN
        composable(ScreenInventory.ADDNEWLOCATION.route) {
            AddNewLocationScreen(
                onNavigateToInventory = { navController.navigate(ScreenInventory.INVENTORIES.route) },
                onNavigateToLocation = { navController.navigate(ScreenInventory.LOCATIONS.route) },
                locationViewModel = locationViewModel
            )
        }

        //EDIT LOCATION SCREEN
        composable(ScreenInventory.EDITLOCATION.route) {
            EditLocationScreen(
                onNavigateToInventory = { navController.navigate(ScreenInventory.INVENTORIES.route) },
                onNavigateToLocation = { navController.navigate(ScreenInventory.LOCATIONS.route) },
                locationViewModel = locationViewModel
            )
        }

        composable(ScreenInventory.LOCATIONDETAILS.route) {
            LocationDetailsScreen(
                onNavigateToLocation = { navController.navigate(ScreenInventory.LOCATIONS.route) },
                locationViewModel = locationViewModel
            )
        }
    }


}