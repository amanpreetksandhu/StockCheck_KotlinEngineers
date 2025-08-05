package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.BlackText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Divider
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.GrayText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.InventoryItemCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun LocationDetailsScreen(
    locationId: String,
    onNavigateToLocation: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToEditInventory:()->Unit,
    onNavigateToItemDetail:()->Unit ,
    locationViewModel: LocationViewModel,
    inventoryViewModel: InventoryViewModel,
    onNavigateToUserProfile:()-> Unit

) {

    var selectedTab by remember { mutableStateOf("Locations") }
    var selectedLocation = locationViewModel.getLocationById(locationId)
    val inventoryList by inventoryViewModel.inventoryItems.collectAsState()

    LaunchedEffect(locationId) {
        inventoryViewModel.loadInventoryByLocation(locationId)
    }
    Scaffold() { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            item {

                TopSection(
                    selectedTab = selectedTab,
                    onTabSelected = {
                        selectedTab = it
                        if (it == "Inventory") {
                            onNavigateToInventory()
                        }
                    }
                )
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                ) {

                    Row {
                        ArrowBackIcon(onNavigateToBackPage = { onNavigateToLocation() })
                        Spacer(modifier = Modifier.width(16.dp))
                        PageHeaderText(headerText = selectedLocation?.name ?: "location name")
                    }

                }
                Divider()
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                        .padding(horizontal = 16.dp)


                ) {
                    Column {
                        GrayText(text = "WAREHOUSE ADDRESS")
                        BlackText(text = "${selectedLocation?.address}, ${selectedLocation?.city}, ${selectedLocation?.country}")
                        Spacer(modifier = Modifier.height(24.dp))
                        Row {
                            Column {
                                GrayText(text = "CONTACT NAME")
                                BlackText(text = selectedLocation?.contactName ?: "contact name")
                                BlackText(
                                    text = selectedLocation?.contactPosition ?: "contact position"
                                )
                            }
                            Spacer(modifier = Modifier.width(50.dp))
                            Column {
                                GrayText(text = "CONTACT INFORMATION")
                                BlackText(text = selectedLocation?.contactPhone ?: "phone number")
                                BlackText(text = selectedLocation?.contactEmail ?: "email")
                            }
                        }
                    }
                }
                Divider()
            }



            items(inventoryList) { item ->
//                Text(item.name)  //Debug
                InventoryItemCard(
                    item = item,
                    onNavigateToItemDetail = { onNavigateToItemDetail() },
                    onDelete = {
                        item.id?.let { id -> inventoryViewModel.deleteInventoryItem(id) }
                    },
                    onEdit = {
                        onNavigateToEditInventory()
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

}
