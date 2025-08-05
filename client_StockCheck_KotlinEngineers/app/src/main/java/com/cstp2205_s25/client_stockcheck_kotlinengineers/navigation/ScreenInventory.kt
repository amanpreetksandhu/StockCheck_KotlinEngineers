package com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation


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
    object ADDNEWINVENTORYITEM : ScreenInventory("add_new_inventory_item")
    object EDITINVENTORYITEM : ScreenInventory("edit_inventory_item")
    object ITEMDETAILSCREEN : ScreenInventory("item_detail_screen")
    object  USERPROFILESCREEN: ScreenInventory("user_profile_screen")

}