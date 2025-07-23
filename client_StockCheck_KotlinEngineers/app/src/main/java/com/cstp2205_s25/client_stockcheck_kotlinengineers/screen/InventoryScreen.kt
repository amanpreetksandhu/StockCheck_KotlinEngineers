package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
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
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.FloatingButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.InventoryItemCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun InventoryScreen(
    onNavigateToLocation: () -> Unit,
    onNavigateToAddNewInventoryItem: () -> Unit,
    onNavigateToEditInventoryItem: () -> Unit,
    onNavigateToItemDetail: () -> Unit,
    inventoryViewModel: InventoryViewModel

) {

    val systemUiController = rememberSystemUiController() // this is to make the top status bar on the phone to make
    // the same color as the top section of the app
    val topSectionColor = Color(0xFF222840)
    var selectedTab by remember { mutableStateOf("Inventory") } // the default selected top bar on the screen
    var searchQuery by remember { mutableStateOf("") }
    val inventoryList = inventoryViewModel.inventoryList.collectAsState().value
    //this is filtered list based on user search
    val filteredInventoryList = inventoryList.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.category.contains(searchQuery, ignoreCase = true)
    }

    LaunchedEffect(Unit) {
        inventoryViewModel.loadInventory()
        systemUiController.setStatusBarColor(
            color = topSectionColor,
            darkIcons = false
        )
    }


    // --------------Scaffold-------------------/
    Scaffold (
        floatingActionButton  = {
            FloatingButton()
        }
    ) { paddingValues ->
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
                    },
                    searchQuery = searchQuery,
                    onSearchQueryChange = {searchQuery = it}
                )
            }

            itemsIndexed(filteredInventoryList) { index, item ->
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    InventoryItemCard(
                        item = item,
                        onEdit = {
                            inventoryViewModel.updateFormField(item)
                            onNavigateToEditInventoryItem()
                        },
                        onDelete = {
                            item.id?.let { id -> inventoryViewModel.deleteInventoryItem(item.id) }
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

