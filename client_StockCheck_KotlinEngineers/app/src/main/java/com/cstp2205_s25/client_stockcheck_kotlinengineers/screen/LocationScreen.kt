package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import android.R
import androidx.compose.material3.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.AddItemDialog
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopBar
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.LocationCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun LocationScreen(onNavigateToInventory: () -> Unit,
                   onNavigateToAddLocation:() -> Unit,
                   locationViewModel: LocationViewModel){

    LaunchedEffect(Unit) {
        locationViewModel.loadLocations()
    }
    val locations by locationViewModel.locations.collectAsState()
    var selectedTab by remember { mutableStateOf("Locations") }
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar (
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    if (it == "Inventory") {
                        onNavigateToInventory()
                    }
                }
            )
        },
        containerColor = Color(0xFF289182)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(top = 20.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .offset(y = (-20).dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    item {
                        PageHeaderSection(
                            headerText = "Locations",
                            onNavigateToAddLocation = { onNavigateToAddLocation() }
                        )
                    }

                    itemsIndexed(locations) { index, location ->
                        LocationCard(
                            location = location,
                            onEditLocation = {
                                locationViewModel.editLocation(location)
                            },
                            onDeleteLocation = {
                                locationViewModel.deleteLocation(location.id)
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(32.dp)) // Add bottom space if needed
                    }
                }
            }



            if (showAddDialog) {
                AddItemDialog(
                    onAdd = {
                        // inventoryViewModel.addItem(it) // if used
                        showAddDialog = false
                    },
                    onDismiss = {
                        showAddDialog = false
                    }
                )
            }
        }
    }
}