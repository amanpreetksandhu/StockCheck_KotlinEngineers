package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

<<<<<<< HEAD
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
=======
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.LocationCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.navigation.ScreenInventory
=======
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.AddItemDialog
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeaderSection
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopBar
import com.cstp2205_s25.client_stockcheck_kotlinengineers.components.LocationCard
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93

@Composable
fun LocationScreen(
    onNavigateToInventory: () -> Unit,
    onNavigateToAddLocation: () -> Unit,
    onNavigateToEditLocation: () -> Unit,
<<<<<<< HEAD
    locationViewModel: LocationViewModel,
    navController: NavController
=======
    locationViewModel: LocationViewModel
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
) {

    LaunchedEffect(Unit) {
        locationViewModel.loadLocations()
    }
<<<<<<< HEAD

    val locations by locationViewModel.locations.collectAsState()
    var selectedTab by remember { mutableStateOf("Locations") }

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
=======
    val locations by locationViewModel.locations.collectAsState()
    var selectedTab by remember { mutableStateOf("Locations") }
    var showAddDialog by remember { mutableStateOf(false) }

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
        containerColor = Color(0xFF289182)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(top = 20.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
//                    .padding(10.dp)
                    .offset(y = (-20).dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    item {
                        PageHeaderSection(
                            headerText = "Locations",
                            onNavigateToAddLocation = { onNavigateToAddLocation() }
                        )
                    }

                    itemsIndexed(locations) { index, location ->
                        LocationCard(
                            location = location,
                            onEditLocation = {
                                onNavigateToEditLocation()
                            },
                            onDeleteLocation = {
                                locationViewModel.deleteLocation(location.id)
                            },
                            locationViewModel = locationViewModel
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(32.dp)) // Add bottom space if needed
                    }
                }
            }



            if (showAddDialog) {
                AddItemDialog(
                    onAdd = {
                        // inventoryViewModel.addItem(it) // if used
                        showAddDialog = false
                    },
                    onDismiss = {
                        showAddDialog = false
                    }
                )
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
            }
        }
    }
}