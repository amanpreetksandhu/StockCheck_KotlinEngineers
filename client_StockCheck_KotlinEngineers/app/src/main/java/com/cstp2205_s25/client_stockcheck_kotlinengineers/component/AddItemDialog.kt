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

@Composable
fun AddItemDialog(
    onAdd: (InventoryItem) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }
    var warehouse by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (name.isNotBlank() && category.isNotBlank() && qty.isNotBlank() && warehouse.isNotBlank()) {
                        val newItem = InventoryItem(
                            name = name,
                            category = category,
                            qty = qty.toIntOrNull() ?: 0,
                            warehouse = warehouse,
                            status = if ((qty.toIntOrNull() ?: 0) > 0) "IN STOCK" else "OUT OF STOCK"
                        )
                        onAdd(newItem)
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add New Inventory Item") },
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