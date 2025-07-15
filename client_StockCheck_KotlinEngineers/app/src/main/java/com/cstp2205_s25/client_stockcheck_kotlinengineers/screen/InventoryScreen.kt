package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory


@Composable
fun InventoryScreen(
    onNavigateToLocation: () -> Unit,
    onNavigateToAddNewInventoryItem: () -> Unit,
    onNavigateToEditInventoryItem: () -> Unit,
    onNavigateToItemDetail: () -> Unit,
    inventoryViewModel: InventoryViewModel

) {
    // INVENTORY management logic-----------------------\
    var selectedTab by remember { mutableStateOf("Inventory") }
    val inventoryItems by inventoryViewModel.inventoryList.collectAsState()

    LaunchedEffect(true) {
        inventoryViewModel.loadInventory()
    }


    // -------------------------------------------/
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 16.dp)
        ) {
            item {
                TopSection(
                    selectedTab = selectedTab,
                    onTabSelected = {
                        selectedTab = it
                        if (it == "Locations") {
                            onNavigateToLocation()
                        }
                    }
                )
                PageHeaderSection(
                    headerText = "Inventory",
                    onNavigateToAddLocation = {},
                    onNavigateToAddNewInventoryItem = {
                        onNavigateToAddNewInventoryItem()
                    }
                )
            }

            itemsIndexed(inventoryItems) { index, item ->
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    InventoryItemCard(
                        item = item,
                        onEdit = {
                            inventoryViewModel.updateFormField(item)
                            onNavigateToEditInventoryItem()
                        },
                        onDelete = {
                            item.id?.let { id -> inventoryViewModel.deleteInventoryItem(id) }
                        },
                        onNavigateToItemDetail = {
                            inventoryViewModel.updateFormField(item)
                            onNavigateToItemDetail()
                        }
                    )
                }
            }
        }
    }
}

