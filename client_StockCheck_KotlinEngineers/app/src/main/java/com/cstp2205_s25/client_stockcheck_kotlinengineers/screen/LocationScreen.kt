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
import androidx.navigation.NavController
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.FloatingButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.LocationCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun LocationScreen(
    onNavigateToInventory: () -> Unit,
    onNavigateToAddLocation: () -> Unit,
    onNavigateToEditLocation: () -> Unit,
    locationViewModel: LocationViewModel,
    navController: NavController,
    onNavigateToUserProfile:()-> Unit
) {

    val systemUiController = rememberSystemUiController()// this is to make the top status bar on the phone to make
    // the same color as the top section of the app
    val topSectionColor = Color(0xFF222840)
    var selectedTab by remember { mutableStateOf("Locations") } // the default selected top bar on the screen
    var searchQuery by remember { mutableStateOf("") }
    val locationsList = locationViewModel.locations.collectAsState().value
    //this is filtered list based on user search
    val filteredLocationList = locationsList.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.address.contains(searchQuery, ignoreCase = true) ||
                it.city.contains(searchQuery, ignoreCase = true)
    }


    LaunchedEffect(Unit) {
        locationViewModel.loadLocations()
        systemUiController.setStatusBarColor(
            color = topSectionColor,
            darkIcons = false
        )
    }


    Scaffold(
        floatingActionButton  = {
            FloatingButton(onNavigateToUserProfile = {onNavigateToUserProfile()})
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
                        if (it == "Inventory") {
                            onNavigateToInventory()
                        }
                    }
                )
                PageHeaderSection(
                    headerText = "Locations",
                    onNavigateToAddLocation = {
                        onNavigateToAddLocation()
                    },
                    onNavigateToAddNewInventoryItem = {},
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it}
                        )
            }

            itemsIndexed(filteredLocationList) { index, location ->
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
                            navController.navigate(
                                ScreenInventory.LOCATIONDETAILS.createRoute(
                                    location.id
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}
