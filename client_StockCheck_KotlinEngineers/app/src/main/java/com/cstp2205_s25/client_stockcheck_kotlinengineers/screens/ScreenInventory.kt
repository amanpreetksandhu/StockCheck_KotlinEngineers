package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

sealed class ScreenInventory (val route: String) {
    object LOGIN: ScreenInventory("login")
    object SIGNUP: ScreenInventory("signup")
    object LOCATIONS: ScreenInventory("locations")
    object INVENTORIES: ScreenInventory("inventories")
}