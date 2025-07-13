package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.LocationCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory

@Composable
fun LocationScreen(
    onNavigateToInventory: () -> Unit,
    onNavigateToAddLocation: () -> Unit,
    onNavigateToEditLocation: () -> Unit,
    locationViewModel: LocationViewModel,
    navController: NavController
) {

    val systemUiController = rememberSystemUiController()
    val topSectionColor = Color(0xFF222840)

    LaunchedEffect(Unit) {
        locationViewModel.loadLocations()
        systemUiController.setStatusBarColor(
            color = topSectionColor,
            darkIcons = false
        )
    }

    val locations by locationViewModel.locations.collectAsState()
    var selectedTab by remember { mutableStateOf("Locations") }

    Scaffold() { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
//                .padding(bottom = 16.dp)
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
                PageHeaderSection(
                    headerText = "Locations",
                    onNavigateToAddLocation = { onNavigateToAddLocation() }
                )
            }

            itemsIndexed(locations) { index, location ->
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    LocationCard(
                        location = location,
                        onEditLocation = {
                            onNavigateToEditLocation()
                        },
                        onDeleteLocation = {
                            locationViewModel.deleteLocation(location.id)
                        },
                        locationViewModel = locationViewModel,
                        onNavigateToLocationDetailsPage = {
                            navController.navigate(ScreenInventory.LOCATIONDETAILS.createRoute(location.id))
                        }
                    )
                }

            }

            item {
                Spacer(modifier = Modifier.height(32.dp)) // Add bottom space if needed
            }
        }
    }
}
