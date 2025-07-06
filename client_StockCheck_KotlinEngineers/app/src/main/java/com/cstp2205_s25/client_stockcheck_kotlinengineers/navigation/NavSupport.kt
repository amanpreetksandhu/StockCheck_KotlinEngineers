package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
<<<<<<< HEAD
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.Location
=======
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.InventoryScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.AddNewLocationScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.EditLocationScreen
<<<<<<< HEAD
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.LocationDetailsScreen
=======
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93

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
<<<<<<< HEAD
                locationViewModel = locationViewModel,
                navController = navController
=======
                locationViewModel = locationViewModel
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
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

<<<<<<< HEAD
        // SELECTED LOCATION DETAILS SCREEN
        composable(
            route = ScreenInventory.LOCATIONDETAILS.route,
            arguments = listOf(navArgument("locationId") { type = NavType.StringType })
        ) { backStackEntry ->
            val locationId = backStackEntry.arguments?.getString("locationId")
            locationId?.let {
                LocationDetailsScreen(
                    locationId = it,
                    onNavigateToLocation = {
                        navController.navigate(ScreenInventory.LOCATIONS.route)
                    },
                    onNavigateToInventory = {
                        navController.navigate(ScreenInventory.INVENTORIES.route)
                    },
                    onNavigateToEditLocation={
                        navController.navigate(ScreenInventory.EDITLOCATION.route)
                    },
                    locationViewModel = locationViewModel
                )
            }
        }

    }
=======
    }


>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
}