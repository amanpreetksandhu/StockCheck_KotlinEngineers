package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.OutlinedCancelButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PageHeader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.PrimaryActionButton
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.RoundedInputField
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Subheader
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TopBar
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.InventoryViewModel
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInventoryItem(
    onNavigateToInventory: () -> Unit,
    onNavigateToLocation: () -> Unit,
    InventoryViewModel: InventoryViewModel,
    LocationViewModel: LocationViewModel,
    onNavigateToUserProfile:()-> Unit
){
    var selectedTab by remember { mutableStateOf("Inventory") }
    val form by InventoryViewModel.inventoryState
    var errorMsg by remember { mutableStateOf("") }

    val locations by LocationViewModel.locations.collectAsState()
    val locationNames = locations.map { it.name }

    // Image Handler Block ========================================================\
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val isUploading = remember { mutableStateOf(false) }

    fun uriToFile(uri: Uri, context: Context): File? {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val tempFile = File.createTempFile("upload", ".jpg", context.cacheDir)
        tempFile.outputStream().use { output -> inputStream.copyTo(output) }
        return tempFile
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
        if (uri != null) {
            val file = uriToFile(uri, context)
            if (file != null) {
                isUploading.value = true
                InventoryViewModel.uploadImageAndUpdateItem(file) { success ->
                    isUploading.value = false
                    if (success) {
                        //  Call update instead of add
                        InventoryViewModel.updateInventoryItem(
                            InventoryViewModel.inventoryState.value
                        ) { updateSuccess ->
                            if (!updateSuccess) {
                                Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Image upload failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Failed to convert image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // =========================================================================/



    LaunchedEffect(Unit) {
        LocationViewModel.loadLocations()
        InventoryViewModel.loadInventory()

    }


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
            Row(modifier = Modifier.padding(top = 10.dp)) {
                ArrowBackIcon(onNavigateToBackPage = { onNavigateToInventory() })
                Spacer(modifier = Modifier.width(10.dp))
                PageHeader(headerText = "Edit Inventory Item")
            }

            Divider(modifier = Modifier.padding(vertical = 20.dp))

            Subheader(text = "Item Details")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray)
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri.value != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri.value),
                        contentDescription = "Selected Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("Tap to select image", color = Color.DarkGray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            RoundedInputField(
                label = "Item Name",
                value = form.name,
                onValueChange = {
                    InventoryViewModel.updateFormField(form.copy(name = it))
                }
            )

            // Description
            RoundedInputField(
                label = "Description",
                value = form.description,
                onValueChange = {
                    InventoryViewModel.updateFormField(form.copy(description = it))
                }
            )


            // Reuse your categories list from Add screen ===========================================\
            val categories = listOf("Electronics", "Clothing", "Furniture", "Supplies")
            var catExpanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = catExpanded,
                onExpandedChange = { catExpanded = !catExpanded }
            ) {
                TextField(
                    readOnly = true,
                    value = form.category,
                    onValueChange = {},
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = catExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = catExpanded,
                    onDismissRequest = { catExpanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                InventoryViewModel.updateFormField(form.copy(category = category))
                                catExpanded = false
                            }
                        )
                    }
                }
            }


            // ===================================================================/

            Divider(modifier = Modifier.padding(vertical = 20.dp))

            Subheader(text = "Item Avaliability")

            // Status radio buttons
            val stockStatus = listOf("In Stock", "Out of Stock")
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                stockStatus.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        RadioButton(
                            selected = form.status == option,
                            onClick = {
                                InventoryViewModel.updateFormField(form.copy(status = option))
                            }
                        )
                        Text(text = option)
                    }
                }
            }

            // Quantity input
            RoundedInputField(
                label = "Quantity",
                value = form.qty.toString(),
                onValueChange = {
                    val qtyInt = it.toIntOrNull() ?: 0
                    InventoryViewModel.updateFormField(form.copy(qty = qtyInt))
                }
            )

            RoundedInputField(
                label = "Price ($ CAD)",
                value = form.price.toString(),
                onValueChange = {
                    val priceDouble = it.toDoubleOrNull() ?: 0.0
                    InventoryViewModel.updateFormField(form.copy(price = priceDouble))
                }
            )


            //Warehouse/Locations ==== Same as Add new Item =======================================\


            var locExpanded by remember { mutableStateOf(false) }
            val selectedLocationName = locations.find { it.id == form.locationId }?.name ?: "Select Location"

            ExposedDropdownMenuBox(
                expanded = locExpanded,
                onExpandedChange = { locExpanded = !locExpanded }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedLocationName,
                    onValueChange = {},
                    label = { Text("Warehouse Location") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = locExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = locExpanded,
                    onDismissRequest = { locExpanded = false }
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location.name) },
                            onClick = {
                                InventoryViewModel.updateFormField(form.copy(locationId = location.id))
                                locExpanded = false
                            }
                        )
                    }
                }
            }
            //===============================================================================/

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {

                OutlinedCancelButton(text = "Cancel",  onClickAction = { onNavigateToInventory() })
                PrimaryActionButton(
                    text = "Save Changes", errorMsg = errorMsg,
                    onClickAction = {
                        InventoryViewModel.updateInventoryItem(form) { success ->
                            if (success) {
                                onNavigateToInventory()
                            } else {
                                errorMsg = "Error updating inventory item"
                            }
                        }
                    },
                    color = Color(0xFF2E66E5)
                )
            }
        }
    }
}
