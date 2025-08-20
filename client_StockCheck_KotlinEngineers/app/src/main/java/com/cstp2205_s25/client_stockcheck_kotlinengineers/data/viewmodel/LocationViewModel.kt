package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.ApiService
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LocationViewModel : ViewModel() {

    private val _locations = MutableStateFlow<List<Location>>(emptyList())

    //exposed
    val locations: StateFlow<List<Location>> = _locations
    val locationState = mutableStateOf(Location())

    // Load all locations from backend
    fun loadLocations() {
        viewModelScope.launch {
            try {
                _locations.value = ApiService.getAllLocations()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    //Get a location by id
    fun getLocationById(id: String): Location? {
        return locations.value.find { it.id == id }
    }

    // Create a new location
    fun createLocation(location: Location, onResult: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            try {
                val success = ApiService.createLocation(location)
                if (success) loadLocations() // reload updated list
                onResult(success)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }


    // Delete a location
    fun deleteLocation(id: String, onResult: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            try {
                val success = ApiService.deleteLocation(id)
                if (success) loadLocations() // Refresh list
                onResult(success)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }

    //clearFormFields
    fun clearFormFields() {
        locationState.value = Location(
            id = "",
            name = "",
            address = "",
            city = "",
            country = "",
            contactName = "",
            contactPosition = "",
            contactEmail = "",
            contactPhone = ""
        )
    }

    // Edit a location

    fun editLocation(location: Location, onResult: (Boolean) -> Unit = {}) {

        viewModelScope.launch {
            try {
                val success = ApiService.editLocation(location)
                if (success) loadLocations()
                onResult(success)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }
    }
}

