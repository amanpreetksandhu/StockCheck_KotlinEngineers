package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Divider
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun LocationDetailsScreen(
    locationId: String,
    onNavigateToLocation: () -> Unit,
    onNavigateToInventory: () -> Unit,
    onNavigateToEditLocation: () -> Unit,
    locationViewModel: LocationViewModel
) {

    var selectedTab by remember { mutableStateOf("Locations") }
    var selectedLocation = locationViewModel.getLocationById(locationId)


//    Text("Details for location ID: $locationId")
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
                        PageHeaderText(headerText = selectedLocation?.name ?: "Warehouse name")
                    }

                }
                Divider()
//                PageHeaderSection(
//                    headerText = "Locations",
//                    onNavigateToAddLocation = { onNavigateToAddLocation() }
//                )
            }

//            itemsIndexed(locations) { index, location ->
//                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//                    LocationCard(
//                        location = location,
//                        onEditLocation = {
//                            onNavigateToEditLocation()
//                        },
//                        onDeleteLocation = {
//                            locationViewModel.deleteLocation(location.id)
//                        },
//                        locationViewModel = locationViewModel,
//                        onNavigateToLocationDetailsPage = {
//                            navController.navigate(ScreenInventory.LOCATIONDETAILS.createRoute(location.id))
//                        }
//                    )
//                }
//
//            }

            item {
                Spacer(modifier = Modifier.height(32.dp)) // Add bottom space if needed
            }
        }
    }

}
