package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.AddItemDialog
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.InventoryContent
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection

@Composable
fun InventoryScreen(
    onNavigateToLocation: () -> Unit,
    inventoryViewModel: InventoryViewModel = viewModel()

){
    // INVENTORY management logic-----------------------\
    var selectedTab by remember { mutableStateOf("Inventory") }
    val inventoryItems by inventoryViewModel.inventoryList.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    // -------------------------------------------/
    Scaffold(
        topBar = {
            TopSection(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    if (it == "Locations") {
                        onNavigateToLocation()
                    }
                }
            )
        },
        containerColor = Color(0xFF289182)
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
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                InventoryContent(
                    inventoryItems = inventoryItems,
                    onDelete = {inventoryViewModel.deleteItem(it)},
                    onEdit = {inventoryViewModel.updateItem(it)},
                    onAdd = {inventoryViewModel.addItem(it)},
                    onAddClick = { showAddDialog = true }
                ) // Your main inventory screen content
            }
            if (showAddDialog) {
                AddItemDialog(
                    onAdd = {
                        inventoryViewModel.addItem(it)
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