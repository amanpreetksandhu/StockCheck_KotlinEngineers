package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.StatusPill
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Subheader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopBar
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.InventoryItem
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun ItemDetailScreen(
    onNavigateToInventory: () -> Unit,
    InventoryViewModel: InventoryViewModel,
    onNavigateToLocation: () -> Unit,
    LocationViewModel: LocationViewModel

) {
    var selectedTab by remember { mutableStateOf("Inventory") }
    val form by InventoryViewModel.inventoryState


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
            Row(modifier = Modifier
                .padding(top = 10.dp),


            ) {
                ArrowBackIcon(onNavigateToBackPage = { onNavigateToInventory() })
                Spacer(modifier = Modifier.width(10.dp))
                PageHeader(headerText = form.name)
                Spacer(modifier = Modifier.width(30.dp))
                IconButton(onClick = { /* Add edit nav here later */ }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Item", tint = Color.Blue,
                        modifier = Modifier.background(Color.LightGray, CircleShape).size(50.dp))
                }
            }

            Divider(modifier = Modifier.padding(vertical = 20.dp))

            // Item Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                if (!form.imageUrl.isNullOrEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(form.imageUrl),
                        contentDescription = "Item Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text("No Image Available", color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = "Description",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray
            )
            Text(
                text = form.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Category and Price
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Column (modifier = Modifier.padding(end = 16.dp)) {
                    Text("Category", color = Color.Gray, fontSize = 14.sp)
                    Text(form.category, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(30.dp))

                Column (modifier = Modifier.padding(start = 16.dp)){
                    Text("Price", color = Color.Gray, fontSize = 14.sp)
                    Text("$${form.price}", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Status and Quantity
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Status", color = Color.Gray, fontSize = 14.sp)
                    Text(form.status ?: "Unknown", fontSize = 16.sp)
                }
                Column {
                    Text("Quantity", color = Color.Gray, fontSize = 14.sp)
                    Text(form.qty.toString(), fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Location
            Column {
                Text("Warehouse Location", color = Color.Gray, fontSize = 14.sp)
                Text(form.locationId ?: "Not Assigned", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

        }
    }
}