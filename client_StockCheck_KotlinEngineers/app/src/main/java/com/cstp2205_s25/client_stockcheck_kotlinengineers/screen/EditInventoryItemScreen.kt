package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.OutlinedCancelButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.RoundedInputField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Subheader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopBar
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun EditInventoryItem(
    onNavigateToInventory: () -> Unit,
    onNavigateToLocation: () -> Unit,
    InventoryViewModel: InventoryViewModel,
    LocationViewModel: LocationViewModel

){
    var selectedTab by remember { mutableStateOf("Inventory") }
    val form = InventoryViewModel.inventoryState.value
    var errorMsg by remember { mutableStateOf("") }

    val locations by LocationViewModel.locations.collectAsState()
    val locationNames = locations.map { it.name }

    LaunchedEffect(Unit) {
        LocationViewModel.loadLocations()
    }


    Scaffold (
        topBar = {
            TopBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    if (it == "Locations") onNavigateToLocation()
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(modifier = Modifier.padding(top = 10.dp)) {
                ArrowBackIcon(onNavigateToBackPage = { onNavigateToInventory() })
                Spacer(modifier = Modifier.width(10.dp))
                PageHeader(headerText = "Edit Inventory Item")
            }
            Divider(modifier = Modifier.padding(vertical = 20.dp))

            Subheader(text = "Item Details")

            RoundedInputField(
                label = "Item Name",
                value = form.name,
                onValueChange = {
                    InventoryViewModel.inventoryState.value = form.copy(name = it)
                }
            )

            // Description is a multiline box input field
            RoundedInputField(
                label = "Description",
                value = form.description,
                onValueChange = {
                    InventoryViewModel.inventoryState.value = form.copy(description = it)
                }
            )

            // Drop down Field for category? -> Is it better to leave it as a text field?


            // ===========================/

            Divider(modifier = Modifier.padding(vertical = 20.dp))

            Subheader(text = "Item Avaliability")

            //Status
            // radio buttons
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                // In Stock

                // Out of Stock

            }

            // Quantity
            RoundedInputField(
                label = "Quantity",
                value = "",
                onValueChange = { }
            )

            //Warehouse =========\
            // Drop down field -> List of the warehouses available in DB

            //===================/

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {

                OutlinedCancelButton(text = "Cancel", onClickAction = { onNavigateToInventory() })
                /*
                 PrimaryActionButton(text = "Add Inventory Item", onClickAction = {
                     InventoryViewModel.createInventoryItem(InventoryViewModel.inventoryState.value) { success ->
                         if (success) {
                             onNavigateToInventory()
                         } else {
                             // Handle error
                 }
                 */


            }


        }
    }
}
