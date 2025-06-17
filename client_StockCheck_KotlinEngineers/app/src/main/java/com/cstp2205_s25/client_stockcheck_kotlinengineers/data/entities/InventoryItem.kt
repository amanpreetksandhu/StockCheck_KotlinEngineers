package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities

import java.util.UUID

data class InventoryItem (
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val category: String,
    val qty: Int,
    val warehouse: String,
    val status: String
)
{}