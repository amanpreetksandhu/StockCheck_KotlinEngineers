package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.LocationCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun LocationScreen(onNavigateToInventory: () -> Unit,
                   locationViewModel: LocationViewModel){
    val locations by locationViewModel.locations.collectAsState()
    Column {
        LocationCard()
    }
}


