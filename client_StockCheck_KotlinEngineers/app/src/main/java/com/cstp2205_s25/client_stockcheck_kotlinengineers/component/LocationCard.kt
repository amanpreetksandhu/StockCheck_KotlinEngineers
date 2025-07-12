package com.cstp2205_s25.client_stockcheck_kotlinengineers.components

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.cstp2205_s25.client_stockcheck_kotlinengineers.R
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.BlackText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.BlueText
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.DeleteDialogBox
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.GrayText
import androidx.compose.ui.unit.sp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.Location
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun LocationCard(
    location: Location,
    onEditLocation: () -> Unit,
    onDeleteLocation: () -> Unit,
    locationViewModel: LocationViewModel,
    onNavigateToLocationDetailsPage: () -> Unit
) {

    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        DeleteDialogBox(
            locationName = location.name,
            onConfirm = {
                onDeleteLocation()
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
        Column(modifier = Modifier.padding(16.dp)) {

            // LOCATION NAME & ICONS
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                //LOCATION NAME
                Column(modifier = Modifier.width(160.dp)) {

                    GrayText(text = "LOCATION NAME")
                    Row(
                        modifier = Modifier
                            .clickable(onClick = { onNavigateToLocationDetailsPage() })
                    ) {
                        BlueText(
                            text = location.name
                        )
                        Image(
                            painterResource(R.drawable.chevron_right),
                            contentDescription = "chevron_right"
                        )
                    }

                }
                Spacer(modifier = Modifier.width(16.dp))
                // CONTACT NAME
                Column {
                    GrayText(
                        text = "CONTACT NAME",
                        )
                    BlackText(
                        text = location.contactName,

                    )
                }

            }

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // ADDRESS
                Column(modifier = Modifier.width(160.dp)) {
                    GrayText(
                        text = "ADDRESS",


                        )
                    BlackText(
                        text = ("${location.address}, ")
                    )
                    BlackText(
                        text = ("${location.city}, ")

                    )
                    BlackText(
                        text = location.country
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    // CONTACT INFO
                    GrayText(
                        text = "CONTACT INFO",


                        )
                    BlackText(
                        text = location.contactEmail

                    )

                    BlackText(
                        text = location.contactPhone
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Edit and Delete Icons
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
                        .clickable {
                            locationViewModel.locationState.value = location
                            onEditLocation()
                        }
                )

            }
        }
    }
}
