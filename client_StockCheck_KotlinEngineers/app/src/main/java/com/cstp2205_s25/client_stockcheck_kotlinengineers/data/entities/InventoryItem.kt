package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities

data class InventoryItem (
    val id: String? = null, // DB is going to generate
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val qty: Int = 0,
    val price: Double = 0.0,
    val imageUrl: String = "",
    val locationId: String? = null,
    val status: String? = null,
)
