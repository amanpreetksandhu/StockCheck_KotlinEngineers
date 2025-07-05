package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.InventoryItemCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopBar
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.InventoryItem

@Composable
fun InventoryScreen(
    onNavigateToLocation: () -> Unit,
    onNavigateToAddNewInventoryItem: () -> Unit,
    onNavigateToEditInventoryItem: () -> Unit,
    inventoryViewModel: InventoryViewModel = viewModel()

) {
    // INVENTORY management logic-----------------------\
    var selectedTab by remember { mutableStateOf("Inventory") }
    val inventoryItems by inventoryViewModel.inventoryList.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    // -------------------------------------------/
    Scaffold(
        topBar = {
            TopBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    if (it == "Locations") { // Switch tabs here
                        onNavigateToLocation()
                    }
                }
            )
        },
        containerColor = Color(0xFF232940)
    )

    { paddingValues ->
        // Main content area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(top = 20.dp) // No extra top padding
//                .background(Color(0xFF289182)) // Ensure the background extends to the top
        ) {
            // White content card
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-20).dp), // Adjust position to overlap with top bar slightly
                shape = RoundedCornerShape(16.dp),
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
                            headerText = "Inventory",
                            onNavigateToAddLocation = {},
                            onNavigateToAddNewInventoryItem = {
                                onNavigateToAddNewInventoryItem()
                            }
                        )

                    }
                    //Lazy Column Content Here
                    items(inventoryItems) { item ->
                        InventoryItemCard(
                            item = item,
                            onEdit = { onNavigateToEditInventoryItem() },
                            onDelete = { inventoryViewModel.deleteItem(item.id) }
                        )
                    }
                }
            }
        }
    }
}

