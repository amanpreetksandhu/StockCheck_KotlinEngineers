package com.cstp2205_s25.client_stockcheck_kotlinengineers.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
<<<<<<< Updated upstream
fun LocationScreen(viewModel: LocationViewModel){
    val locations by viewModel.locations.collectAsState()
    Column {  }
}
=======
fun LocationScreen(onNavigateToInventory: () -> Unit,
                   onNavigateToAddLocation:() -> Unit,
                   locationViewModel: LocationViewModel){

    LaunchedEffect(Unit) {
        locationViewModel.loadLocations()
    }
    val locations by locationViewModel.locations.collectAsState()
    var selectedTab by remember { mutableStateOf("Locations") }
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar (
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    if (it == "Inventory") {
                        onNavigateToInventory()
                    }
                }
            )
        },
        containerColor = Color(0xFF232940)
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
                    //.padding(10.dp)
                    .offset(y = (-20).dp),
                shape = RoundedCornerShape(16.dp),
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
                                locationViewModel.editLocation(location)
                            },
                            onDeleteLocation = {
                                locationViewModel.deleteLocation(location.id)
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(32.dp)) // Add bottom space if needed
                    }
                }
            }
>>>>>>> Stashed changes


