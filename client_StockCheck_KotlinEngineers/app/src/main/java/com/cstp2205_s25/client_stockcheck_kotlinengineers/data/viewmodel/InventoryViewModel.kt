package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.InventoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.ApiService
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.Location
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class InventoryViewModel : ViewModel() {


    private val _inventoryItems = MutableStateFlow<List<InventoryItem>>(emptyList())
    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>> = _locations

    // exposed to use
    val inventoryItems: StateFlow<List<InventoryItem>> = _inventoryItems

    private val _inventoryList = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventoryList: StateFlow<List<InventoryItem>> = _inventoryList.asStateFlow()

    private val _inventoryState = mutableStateOf(InventoryItem())
    val inventoryState: State<InventoryItem> = _inventoryState

    fun updateFormField(update: InventoryItem) {
        _inventoryState.value = update
    }

    fun clearFormFields() {
        _inventoryState.value = InventoryItem()
    }

    fun loadInventory() {
        viewModelScope.launch {
            try {
                val items = ApiService.getAllInventoryItems()
                Log.d("LOAD_INVENTORY", "Loaded ${items.size} items: $items")
                _inventoryList.value = items
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("LOAD_INVENTORY", "Error loading inventory: ${e.message}")
            }
        }
    }

    fun loadLocations(){
        viewModelScope.launch {
            try {
                val locations = ApiService.getAllLocations()
                _locations.value = locations
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("LOAD_LOCATIONS", "Error loading locations: ${e.message}")
            }
        }
    }

    fun addInventoryItem(item: InventoryItem, onResult: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            try {
                Log.d("DEBUG", "Calling API to add item: $item")
                val success = ApiService.addInventoryItem(item)
                Log.d("DEBUG", "API call result: $success")
                if (success) loadInventory()
                onResult(success)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("DEBUG", "Exception during API call: ${e.message}")
                onResult(false)
            }
        }
    }

    fun deleteInventoryItem(itemId: String, onResult: (Boolean) -> Unit = {}) {
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

    fun updateInventoryItem(updatedItem: InventoryItem, onResult: (Boolean) -> Unit = {}) {
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

    fun loadInventoryByLocation(locationId: String) {
        viewModelScope.launch {
            try {
                _inventoryItems.value = ApiService.getInventoriesByLocation(locationId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun uploadImageAndUpdateItem(imageFile: File, onResult: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            try {
                val uploadedUrl = ApiService.uploadImage(imageFile)
                if (uploadedUrl != null) {
                    // Update the current item with new image URL
                    val updatedItem = _inventoryState.value.copy(imageUrl = uploadedUrl)
                    _inventoryState.value = updatedItem
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }


}
