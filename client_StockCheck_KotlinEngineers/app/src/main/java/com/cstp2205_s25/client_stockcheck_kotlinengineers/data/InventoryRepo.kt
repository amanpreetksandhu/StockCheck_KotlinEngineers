package com.cstp2205_s25.client_stockcheck_kotlinengineers.data

import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.ApiService
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.InventoryItem

object InventoryRepo {

    suspend fun getAllInventoryItems(): List<InventoryItem> {
        return ApiService.getAllInventoryItems()

    }

    suspend fun addInventoryItem(item: InventoryItem): Boolean {
        return ApiService.addInventoryItem(item)
    }

    suspend fun editInventoryItem(item: InventoryItem): Boolean {
        return ApiService.editInventoryItem(item)
    }

    suspend fun deleteInventoryItem(id: String): Boolean {
        return ApiService.deleteInventoryItem(id)

    }

}