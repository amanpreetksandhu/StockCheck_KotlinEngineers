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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Button.OutlinedCancelButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Button.PrimaryActionButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.RoundedInputField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Subheader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopBar


@Composable
fun AddNewLocationScreen(
    onNavigateToInventory: () -> Unit,
    onNavigateToLocation: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("Locations") }
    Scaffold(
        topBar = {
            TopBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    if (it == "Inventory") {
                        onNavigateToInventory()
                    }
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
                ArrowBackIcon(onNavigateToBackPage = { onNavigateToLocation() })
                Spacer(modifier = Modifier.width(10.dp))
                PageHeader(headerText = "Add New Location")
            }
            Divider(modifier = Modifier.padding(vertical = 20.dp))

            Subheader(text = "Location Details")

            RoundedInputField(label = "Location Name")
            RoundedInputField(label = "Street Address")
            RoundedInputField(label = "City")
            RoundedInputField(label = "Country")

            Divider(modifier = Modifier.padding(vertical = 20.dp))

            Subheader(text = "Contact Details")

            RoundedInputField(label = "Contact Name")
            RoundedInputField(label = "Position")
            RoundedInputField(label = "Phone Number")
            RoundedInputField(label = "Email")

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedCancelButton(text = "Cancel", onClickAction = { onNavigateToLocation() })
                PrimaryActionButton(text = "Add Location", onClickAction = ({}))
            }
        }
    }
}


