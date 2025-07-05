package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.InventoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.ApiService

class InventoryViewModel: ViewModel() {

    // To be altered to pull from DB
    private val _inventoryList = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventoryList: StateFlow<List<InventoryItem>> = _inventoryList

    val inventoryState = mutableStateOf(InventoryItem())

    fun loadInventory() {
        viewModelScope.launch {
            try {
                _inventoryList.value = ApiService.getAllInventoryItems()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun addItem(item: InventoryItem, onResult: (Boolean) -> Unit = {}) {
        viewModelScope.launch{
            try {
                val success = ApiService.addInventoryItem(item)
                if (success) loadInventory()
                onResult(success)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }

        }

    }

    fun deleteItem( itemId: String, onResult: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            try {
                val success = ApiService.deleteInventoryItem(itemId)
                if (success) loadInventory()
                onResult(success)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }

    fun updateItem(updatedItem: InventoryItem, onResult: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            try {
                val success = ApiService.editInventoryItem(updatedItem)
                if (success) loadInventory()
                onResult(success)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }

    fun clearFormFields() {
        inventoryState.value = InventoryItem(
            name = "",
            description = "",
            category = "",
            status = "",
            qty = 0,
            price = 0.0,
            imageUrl = "",
            locationId = "",
            id = ""
        )
    }


}