package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.InventoryItem
val DarkPrimary = Color(0xFF2C3E50) // Dark blue/grey for top bar
val LightBlueAccent = Color(0xFF3498DB) // Blue for active tabs, buttons
val GreenStatus = Color(0xFF2ECC71) // Green for 'IN STOCK'
val OrangeStatus = Color(0xFFE67E22) // Orange for 'OUT OF STOCK'
val TextGrey = Color(0xFFBDC3C7) // Light grey for secondary text


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryContent(
    inventoryItems: List<InventoryItem>,
    onDelete: (String) -> Unit,
    onEdit: (InventoryItem) -> Unit,
    onAdd: (InventoryItem) -> Unit,
    onAddClick: () -> Unit



) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Inventory",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF289182),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Search Bar
        OutlinedTextField(
            value = "", // Replace with actual state
            onValueChange = { /* Handle search input */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text("Search...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(

            )
            );



        // Add New Item Button
        Button (
            onClick = { onAddClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LightBlueAccent),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add, // Assuming '+' icon from Add
                contentDescription = "Add New Item",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add New Item", color = Color.White, fontSize = 18.sp)
        }

        // Inventory Items List (using LazyColumn for scrollability)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(inventoryItems) { item ->
                InventoryItemCard(
                    item = item,
                    onDelete = { onDelete(item.id ?: "") },
                    onEdit = { onEdit(item) },
                    onNavigateToItemDetail = { /* Handle navigation to item detail */ },


                )
            }
            // Add more items here dynamically if you have a list
        }
    }
}
