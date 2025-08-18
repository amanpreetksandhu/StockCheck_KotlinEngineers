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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.ArrowBackIcon
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.BWExposedDropdown
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
fun AddNewInventoryItemScreen(
    onNavigateToInventory: () -> Unit,
    onNavigateToLocation: () -> Unit,
    InventoryViewModel: InventoryViewModel,
    LocationViewModel: LocationViewModel,
    onNavigateToUserProfile:()-> Unit
){

    LaunchedEffect(Unit) { // Always start screen with empty fields; Ready to go
        InventoryViewModel.clearFormFields()
        LocationViewModel.loadLocations()

    }

    var selectedTab by remember { mutableStateOf("Inventory") }
    val form by InventoryViewModel.inventoryState
    var errorMsg = ""

    // List of locations need to be fetched here to match inventory items with their current location
    val locationsList by LocationViewModel.locations.collectAsState()

    // Image Handler Block ========================================================\
    val context = LocalContext.current // this is for the image picker
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val isUploading = remember { mutableStateOf(false) }

    // Not sure where to put this....
    fun uriToFile(uri: Uri, context: Context): File? {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val tempFile = File.createTempFile("upload", ".jpg", context.cacheDir)
        tempFile.outputStream().use { output ->
            inputStream.copyTo(output)
        }
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
                        // Save the item after image is uploaded and URL is set
                        InventoryViewModel.addInventoryItem(
                            InventoryViewModel.inventoryState.value
                        ) { itemSuccess ->
                            if (!itemSuccess) {
                                Toast.makeText(context, "Item save failed", Toast.LENGTH_SHORT).show()
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

    Scaffold ( // Keep navigation between tabs active always
        topBar = {
            TopBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    if (it == "Locations") {
                        onNavigateToLocation()
                    }
                }
            )
        },
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            Row (modifier = Modifier.padding(top = 10.dp)){
                ArrowBackIcon(onNavigateToBackPage = { onNavigateToInventory() })
                Spacer(modifier = Modifier.width(10.dp))
                PageHeader(headerText = "Add New Item")
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

            // Description is a multiline box input field
            RoundedInputField( // to be alter or change to match purpose
                label = "Description",
                value = form.description,
                onValueChange = {
                    InventoryViewModel.updateFormField(form.copy(description = it))
                }
            )

            // Drop down Field for category
            /*
            4 default
            feature to add, remove or edit category
            */
            val categories = listOf("Electronics", "Clothing", "Furniture","Supplies") //

            BWExposedDropdown(
                label = "Select Category",
                value = form.category,
                options = categories,
                { category ->
                    InventoryViewModel.updateFormField(form.copy(category = category))
                }
            )



            // ====================================================================================/

            Divider(modifier = Modifier.padding(vertical = 20.dp))


            // Quantity
            RoundedInputField(
                label = "Quantity",
                value = form.qty.toString(),
                onValueChange = {
                    val qtyInt = it.toIntOrNull() ?: 0 // Handle non-integer input
                    InventoryViewModel.updateFormField(form.copy(qty = qtyInt))

                }
            )

            // price
            RoundedInputField(
                label = "Price ($ CAD)",
                value = form.price.toString(),
                onValueChange = {
                    val price = it.toDoubleOrNull()  // Handle non-Double input
                    if (price != null){
                        InventoryViewModel.updateFormField(form.copy(price = price))
                    }

                },

            )

            //Warehouse / Locations ==================================\
            // Drop down field -> List of the warehouses/locations available in DB
            //var locExpanded by remember { mutableStateOf(false) }

            val selectedLocationName = locationsList.find { it.id == form.locationId }?.name ?: ""

            BWExposedDropdown(
                label = "Select Location",
                value = selectedLocationName,
                options = locationsList.map { it.name },
                onSelected = { selectedName ->
                    val selectedLocation = locationsList.find { it.name == selectedName }
                    if (selectedLocation != null) {
                        InventoryViewModel.updateFormField(form.copy(locationId = selectedLocation.id))
                    }
                }
            )

            /*ExposedDropdownMenuBox(
                expanded = locExpanded,
                onExpandedChange = { locExpanded = !locExpanded },

            ) {
                TextField(
                    readOnly = true,
                    value = selectedLocationName,
                    label = { Text(" Select Location") },
                    onValueChange = {},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = locExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()

                )

                ExposedDropdownMenu(
                    expanded = locExpanded,
                    onDismissRequest = { locExpanded = false },

                ) {
                    locationsList.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location.name) },
                            onClick = {
                                InventoryViewModel.updateFormField(form.copy(locationId = location.id))
                                locExpanded = false
                            }
                        )
                    }
                }
            }*/

            //=========================================================/

            Spacer(modifier = Modifier.height(32.dp))

            Row( // bottom buttons
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween)
            {

                OutlinedCancelButton(text = "Cancel", onClickAction = { onNavigateToInventory() })

                PrimaryActionButton(text = "Add Item", errorMsg = errorMsg,
                    onClickAction = {
                    Log.d("ButtonClick", "Add Inventory Item clicked")
                    Log.d("FormData", InventoryViewModel.inventoryState.value.toString())

                    InventoryViewModel.addInventoryItem(InventoryViewModel.inventoryState.value) { success ->
                        if (success) {
                            Log.d("DEBUG", "Item successfully created!")
                            onNavigateToInventory()
                        } else {
                            Log.d("DEBUG", "Failed to create item.")
                            errorMsg = "Error adding inventory item"
                        }
                    }

                },
                    color = Color(0xFF2E66E5)
                )
            }
        }
    }
}
