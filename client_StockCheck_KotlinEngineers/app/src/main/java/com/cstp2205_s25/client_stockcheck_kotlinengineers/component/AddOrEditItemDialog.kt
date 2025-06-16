package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entitie.InventoryItem
import java.util.UUID

@Composable
fun AddOrEditItemDialog(
    initialItem: InventoryItem? = null,
    onConfirm: (InventoryItem) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf(initialItem?.name ?: "") }
    var category by remember { mutableStateOf(initialItem?.category ?: "") }
    var qty by remember { mutableStateOf(initialItem?.qty?.toString() ?: "") }
    var warehouse by remember { mutableStateOf(initialItem?.warehouse ?: "") }
    var status by remember { mutableStateOf(initialItem?.status ?: "IN STOCK") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                        val updateItem = InventoryItem(
                            id = initialItem?.id ?: UUID.randomUUID().toString(),
                            name = name,
                            category = category,
                            qty = qty.toIntOrNull() ?: 0,
                            warehouse = warehouse,
                            status = status
                        )
                        onConfirm(updateItem)
                }) {
                Text(if (initialItem == null)"Add" else "Update")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text(if (initialItem == null) "Add New Item" else "Edit Item") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Item Name") })
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
                OutlinedTextField(value = qty, onValueChange = { qty = it }, label = { Text("Quantity") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                OutlinedTextField(value = warehouse, onValueChange = { warehouse = it }, label = { Text("Warehouse") })
            }
        }
    )
}