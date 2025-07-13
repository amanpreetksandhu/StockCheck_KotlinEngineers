package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.R
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.InventoryItem


@Composable
fun InventoryItemCard(
    onNavigateToItemDetail: () -> Unit,
    item: InventoryItem,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {

    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        DeleteDialogBox(
            name = item.name,
            onConfirm = {
                onDelete()
                showDeleteDialog = false
            },
            onDismiss = {
                showDeleteDialog = false
            }

        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Item Name and Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Column {
                        GrayText(text = "INVENTORY ITEM")

                        Row(
                            modifier = Modifier
                                .clickable(onClick = { onNavigateToItemDetail() })

                        ) {
                            BlueText(text = item.name)
                            Image(
                                painterResource(R.drawable.chevron_right),
                                contentDescription = "chevron_right"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(30.dp))
                StatusPill(status = item.status ?: "Unknown")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Category, Qty, Warehouse
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    GrayText(text ="CATEGORY")
                    BlackText(text = item.category)
                }
                Column {
                    GrayText(text ="QTY")
                    BlackText(text = item.qty.toString())
                }
                Column {
                    GrayText(text ="LOCATION")
                    BlackText(text = item.locationId ?: "Unknown")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Delete and Edit Icons
            Row() {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color(0xFFC94414),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { showDeleteDialog = true }
                )
                Spacer(modifier = Modifier.width(270.dp))
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color(0xFF2E66E5),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onEdit() }
                )
            }
        }
    }
}
