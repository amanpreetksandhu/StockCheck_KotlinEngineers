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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.OutlinedCancelButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PrimaryActionButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.RoundedInputField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Subheader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun AddNewLocationScreen(
    onNavigateToInventory: () -> Unit,
    onNavigateToLocation: () -> Unit,
    locationViewModel: LocationViewModel,
    onNavigateToUserProfile:()-> Unit

) {

    LaunchedEffect(Unit) {
        locationViewModel.clearFormFields()
    }

    var selectedTab by remember { mutableStateOf("Locations") }
    val form = locationViewModel.locationState.value
    var errorMsg = ""

    Scaffold(
        topBar = {

            TopSection(

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

            RoundedInputField(
                label = "Location Name",
                value = form.name,
                onValueChange = { locationViewModel.locationState.value = form.copy(name = it) })
            RoundedInputField(
                label = "Street Address",
                value = form.address,
                onValueChange = { locationViewModel.locationState.value = form.copy(address = it) })
            RoundedInputField(
                label = "City",
                value = form.city,
                onValueChange = { locationViewModel.locationState.value = form.copy(city = it) })
            RoundedInputField(
                label = "Country",
                value = form.country,
                onValueChange = { locationViewModel.locationState.value = form.copy(country = it) })

            Divider(modifier = Modifier.padding(vertical = 20.dp))

            Subheader(text = "Contact Details")

            RoundedInputField(
                label = "Contact Name",
                value = form.contactName,
                onValueChange = {
                    locationViewModel.locationState.value = form.copy(contactName = it)
                })
            RoundedInputField(
                label = "Position",
                value = form.contactPosition,
                onValueChange = {
                    locationViewModel.locationState.value = form.copy(contactPosition = it)
                })
            RoundedInputField(
                label = "Phone Number",
                value = form.contactPhone,
                onValueChange = {
                    locationViewModel.locationState.value = form.copy(contactPhone = it)
                })
            RoundedInputField(
                label = "Email",
                value = form.contactEmail,
                onValueChange = {
                    locationViewModel.locationState.value = form.copy(contactEmail = it)
                })

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedCancelButton(text = "Cancel", onClickAction = { onNavigateToLocation() })

                PrimaryActionButton(
                    text = "Add Location",
                    errorMsg = errorMsg,
                    onClickAction = {
                        locationViewModel.createLocation(locationViewModel.locationState.value) { success ->
                            if (success) {
                                onNavigateToLocation()
                            } else {
                                errorMsg = "Add locationError while posting new location"
                            }
                        }
                    },
                    color = Color(0xFF2E66E5)
                )
            }
        }
    }
}


