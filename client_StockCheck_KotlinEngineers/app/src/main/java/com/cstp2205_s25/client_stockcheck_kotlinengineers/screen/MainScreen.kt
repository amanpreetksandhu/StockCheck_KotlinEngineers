package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.AddItemDialog
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.InventoryContent
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TabItem
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel

@Composable
fun MainScreen(
    inventoryViewModel: InventoryViewModel = viewModel()

){
    // INVENTORY management logic-----------------------\
    var selectedTab by remember { mutableStateOf("Inventory") }
    val inventoryItems by inventoryViewModel.inventoryList.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    // -------------------------------------------/
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF289182)) // Green
                    .padding(vertical = 16.dp)
            ) {
                // App Logo and Refresh Icon
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "STOCKCHECK",
                            color = androidx.compose.ui.graphics.Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp)) // Spacing between logo and tabs

                // Segmented Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp) // Spacing between tabs
                ) {
                    TabItem(
                        text = "Warehouses",
                        isSelected = selectedTab == "Warehouses",
                        onClick = { selectedTab = "Warehouses" }
                    )
                    TabItem(
                        text = "Inventory",
                        isSelected = selectedTab == "Inventory",
                        onClick = { selectedTab = "Inventory" }
                    )
                }
            }
        },
        containerColor = Color(0xFF289182)// Set scaffold background to match top bar
    ) { paddingValues ->
        // Main content area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(top = 0.dp) // No extra top padding
                .background(Color(0xFF289182)) // Ensure the background extends to the top
        ) {
            // White content card
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-20).dp), // Adjust position to overlap with top bar slightly
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
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