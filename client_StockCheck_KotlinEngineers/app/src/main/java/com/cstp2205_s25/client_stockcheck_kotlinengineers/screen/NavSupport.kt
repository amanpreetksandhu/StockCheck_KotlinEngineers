package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.AuthViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screens.LoginScreen
import com.cstp2205_s25.client_stockcheck_kotlinengineers.screens.SignupScreen

@Composable
fun NavSupport(vm: AuthViewModel) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = vm


    NavHost(navController = navController, startDestination = ScreenInventory.LOGIN.route){

        // LOGIN ROUTES LOGIC----------------------------------------------\
        composable(ScreenInventory.LOGIN.route){
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(ScreenInventory.INVENTORY.route){
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
                navController.navigate(ScreenInventory.INVENTORY.route) {
                    popUpTo(ScreenInventory.SIGNUP.route) { inclusive = true }
                }
            },
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }
        composable(ScreenInventory.INVENTORY.route) {
            MainScreen() // This is the missing piece!
        }



        // ---------------- NavHost Boudary ------------------//
    }





}