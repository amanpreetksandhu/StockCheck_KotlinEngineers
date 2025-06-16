package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun NavSupport(vm: AuthViewModel) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val locationViewModel: LocationViewModel = viewModel()

    NavHost(navController = navController, startDestination = ScreenInventory.LOGIN.route){

        // LOGIN ROUTES LOGIC----------------------------------------------\
        composable(ScreenInventory.LOGIN.route){
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(ScreenInventory.LOCATIONS.route){
                        popUpTo(ScreenInventory.LOGIN.route) {inclusive = true}
                    }
                },
                onNavigateToSignup = {
                    navController.navigate(ScreenInventory.SIGNUP.route)
                },
                authViewModel = authViewModel
            )
        }

        // SIGNUP ROUTES LOGIC ---------------------------------------\
        composable(ScreenInventory.SIGNUP.route){
            SignupScreen(onSignupSuccess = {
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
        composable(ScreenInventory.LOCATIONS.route) {
            LocationScreen(onNavigateToInventory = {navController.navigate(ScreenInventory.INVENTORIES.route)},
                locationViewModel = locationViewModel)

        }


    }





}