package com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation

<<<<<<< HEAD
sealed class ScreenInventory(val route: String) {
    object LOGIN : ScreenInventory("login")
    object SIGNUP : ScreenInventory("signup")
    object LOCATIONS : ScreenInventory("locations")
    object INVENTORIES : ScreenInventory("inventory")
    object ADDNEWLOCATION : ScreenInventory("add_new_location")
    object EDITLOCATION : ScreenInventory("edit_location")
    object LOCATIONDETAILS : ScreenInventory("locationDetails/{locationId}") {
        fun createRoute(locationId: String) = "locationDetails/$locationId"
    }
=======
sealed class ScreenInventory (val route: String) {
    object LOGIN: ScreenInventory("login")
    object SIGNUP: ScreenInventory("signup")
    object LOCATIONS: ScreenInventory("locations")
    object INVENTORIES: ScreenInventory("inventory")
    object ADDNEWLOCATION: ScreenInventory("add_new_location")
    object EDITLOCATION: ScreenInventory("edit_location")
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
}