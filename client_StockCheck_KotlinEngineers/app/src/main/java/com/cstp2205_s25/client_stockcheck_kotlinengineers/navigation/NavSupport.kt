package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory

import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.AddNewInventoryItemScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.InventoryScreen

import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.AddNewLocationScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.EditInventoryItem
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.EditLocationScreen

import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.InventoryScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.LocationDetailsScreen

import com.cstp2205_s25.client_stockcheck_kotlinengineers.screen.ItemDetailScreen


@Composable
fun NavSupport(vm: AuthViewModel) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val locationViewModel: LocationViewModel = viewModel()
    val InventoryViewModel: InventoryViewModel = viewModel()

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

        //LOCATIONS NAVIGATION BLOCK---------------------------------------------\
        composable(ScreenInventory.LOCATIONS.route) {
            LocationScreen(
                onNavigateToInventory = { navController.navigate(ScreenInventory.INVENTORIES.route) },
                onNavigateToAddLocation = { navController.navigate(ScreenInventory.ADDNEWLOCATION.route) },
                onNavigateToEditLocation = { navController.navigate(ScreenInventory.EDITLOCATION.route) },
                locationViewModel = locationViewModel,
                navController = navController

            )

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
                    onNavigateToEditLocation = {
                        navController.navigate(ScreenInventory.EDITLOCATION.route)
                    },
                    locationViewModel = locationViewModel
                )
            }
        }



        //INVENTORY NAVIGATION BLOCK------------------------------------------------\
        composable(ScreenInventory.INVENTORIES.route) {
            InventoryScreen(onNavigateToLocation = { navController.navigate(ScreenInventory.LOCATIONS.route) },
                onNavigateToAddNewInventoryItem = { navController.navigate(ScreenInventory.ADDNEWINVENTORYITEM.route) },
                onNavigateToEditInventoryItem = { navController.navigate(ScreenInventory.EDITINVENTORYITEM.route) },
                onNavigateToItemDetail = { navController.navigate(ScreenInventory.ITEMDETAILSCREEN.route) },
                inventoryViewModel = InventoryViewModel
            )
        }

        // ADD
        composable(ScreenInventory.ADDNEWINVENTORYITEM.route) {
            AddNewInventoryItemScreen(
                onNavigateToInventory = { navController.navigate(ScreenInventory.INVENTORIES.route) },
                onNavigateToLocation = { navController.navigate(ScreenInventory.LOCATIONS.route) },
                InventoryViewModel = InventoryViewModel,
                LocationViewModel = locationViewModel
            )
        }

        // UPDATE/EDIT
        composable(ScreenInventory.EDITINVENTORYITEM.route) {
            EditInventoryItem(
                onNavigateToInventory = { navController.navigate(ScreenInventory.INVENTORIES.route) },
                onNavigateToLocation = { navController.navigate(ScreenInventory.LOCATIONS.route) },
                InventoryViewModel = InventoryViewModel,
                LocationViewModel = locationViewModel
            )
        }

        composable(ScreenInventory.ITEMDETAILSCREEN.route) {
            ItemDetailScreen(
                onNavigateToInventory = {navController.navigate(ScreenInventory.INVENTORIES.route)},
                LocationViewModel = locationViewModel,
                onNavigateToLocation = {navController.navigate(ScreenInventory.LOCATIONS.route)},
                onNavigateToEditInventoryItem = { navController.navigate(ScreenInventory.EDITINVENTORYITEM.route) },
                InventoryViewModel = InventoryViewModel
            )
        }
    }
}