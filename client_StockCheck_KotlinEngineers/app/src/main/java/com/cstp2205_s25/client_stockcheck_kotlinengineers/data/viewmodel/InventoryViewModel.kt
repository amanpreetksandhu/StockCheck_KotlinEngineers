package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel

import androidx.lifecycle.ViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entitie.InventoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InventoryViewModel: ViewModel() {

    // To be altered to pull from DB
    private val _inventoryList = MutableStateFlow(
        listOf(
            InventoryItem("1", "Television", "Electronics", 5, "Manhattan", "IN STOCK"),
            InventoryItem("2", "Gym Bag", "Accessories", 0, "Los Angeles", "OUT OF STOCK")
        )
    )
    val inventoryList = _inventoryList.asStateFlow()

    // You’ll use this function in CREATE later
    fun addItem(item: InventoryItem) {
        _inventoryList.value = _inventoryList.value + item
    }

    fun deleteItem(itemId: String) {
        _inventoryList.value = _inventoryList.value.filterNot { it.id == itemId }
    }

    fun updateItem(updatedItem: InventoryItem) {
        _inventoryList.value = _inventoryList.value.map {
            if (it.id == updatedItem.id) updatedItem else it
        }
    }


}