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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.OutlinedCancelButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PrimaryActionButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.RoundedInputField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Subheader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopBar
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewInventoryItemScreen(
    onNavigateToInventory: () -> Unit,
    onNavigateToLocation: () -> Unit,
    InventoryViewModel: InventoryViewModel,
    LocationViewModel: LocationViewModel

){

    LaunchedEffect(Unit) { // Always start screen with empty fields; Ready to go
        InventoryViewModel.clearFormFields()
        LocationViewModel.loadLocations()

    }

    var selectedTab by remember { mutableStateOf("Inventory") }
    var form = InventoryViewModel.inventoryState.value
    var errorL = ""

    // List of locations need to be fetched here to match inventory items with their current location
    val locationsList by LocationViewModel.locations.collectAsState()

    Scaffold ( // Keep navigation between tabs active always
        topBar = {
            TopBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    if (it == "Locations") {
                        onNavigateToLocation()
                    }
                }
            )
        },
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            Row (modifier = Modifier.padding(top = 10.dp)){
                ArrowBackIcon(onNavigateToBackPage = { onNavigateToInventory() })
                Spacer(modifier = Modifier.width(10.dp))
                PageHeader(headerText = "Add New Inventory Item")
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
            RoundedInputField( // to be alter or change to match purpose
                label = "Description",
                value = form.description,
                onValueChange = {
                    InventoryViewModel.inventoryState.value = form.copy(description = it)
                }
            )

            // Drop down Field for category
            /*
            4 default
            feature to add, remove or edit category
            */
            val categories = listOf("Electronics", "Clothing", "Furniture","Supplies") //
            var catExpanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox( // @OptIn(ExperimentalMaterial3Api::class) ---> needed to work
                expanded = catExpanded,
                onExpandedChange = { catExpanded = !catExpanded }
            ){
                TextField(
                    readOnly = true,
                    value = form.category,
                    onValueChange = {},
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = catExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = catExpanded,
                    onDismissRequest = { catExpanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                InventoryViewModel.inventoryState.value = form.copy(category = category)
                                catExpanded = false
                            }
                        )
                    }
                }
            }

            // ===========================/

            Divider(modifier = Modifier.padding(vertical = 20.dp))

            Subheader(text = "Item Avaliability")

            //Status
            // radio buttons
            Row (modifier = Modifier.padding(vertical = 10.dp)){
                // In Stock (side by side) Out of Stock
                val stockStatus = listOf("In Stock", "Out of Stock")

                stockStatus.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        RadioButton(
                            selected = form.status == option,
                            onClick = {
                                InventoryViewModel.inventoryState.value = form.copy(status = option)
                            }
                        )
                        Text(text = option)
                    }
                }

            }

            // Quantity
            RoundedInputField(
                label = "Quantity",
                value = form.qty.toString(),
                onValueChange = {
                    val qtyInt = it.toIntOrNull() ?: 0 // Handle non-integer input
                    InventoryViewModel.inventoryState.value = form.copy(qty = qtyInt)

                }
            )

            //Warehouse / Locations =========\
            // Drop down field -> List of the warehouses/locations available in DB
            var locExpanded by remember { mutableStateOf(false) }

            val selectedLocationName = locationsList.find { it.id == form.locationId }?.name ?: "Select Location"

            ExposedDropdownMenuBox(
                expanded = locExpanded,
                onExpandedChange = { locExpanded = !locExpanded }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedLocationName,
                    onValueChange = {},
                    label = { Text("Warehouse Location") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = locExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = locExpanded,
                    onDismissRequest = { locExpanded = false }
                ) {
                    locationsList.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location.name) },
                            onClick = {
                                InventoryViewModel.inventoryState.value = form.copy(locationId = location.id)
                                locExpanded = false
                            }
                        )
                    }
                }
            }



            //===================/

            Spacer(modifier = Modifier.height(32.dp))

            Row( // bottom buttons
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween)
            {

                OutlinedCancelButton(text = "Cancel", onClickAction = { onNavigateToInventory() })

                PrimaryActionButton(text = "Add Inventory Item",eroorMessage = errorL, onClickAction = {
                    InventoryViewModel.addItem(InventoryViewModel.inventoryState.value) { success ->
                        if (success) {
                            onNavigateToInventory()
                        } else {
                            errorL = "Error adding inventory item"
                        }
                    }

                })
            }
        }
    }
}