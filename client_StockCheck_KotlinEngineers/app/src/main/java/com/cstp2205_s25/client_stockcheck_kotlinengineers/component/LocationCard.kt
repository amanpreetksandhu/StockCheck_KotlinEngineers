package com.cstp2205_s25.client_stockcheck_kotlinengineers.components

import androidx.compose.foundation.Image
import com.cstp2205_s25.client_stockcheck_kotlinengineers.R
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.DarkPrimary
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TextGrey
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
                    Text(
                        text = "LOCATION NAME",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextGrey
                    )

                    Row(
                        modifier = Modifier
                            .clickable(onClick = { onNavigateToLocationDetailsPage() })
                    ) {
                        Text(
                            text = location.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF2E66E5)
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
                    Text(
                        text = "CONTACT NAME",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextGrey
                    )
                    Text(
                        text = location.contactName,
                        fontSize = 14.sp,
                        color = DarkPrimary
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
                    Text(
                        text = "ADDRESS",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextGrey
                    )
                    Text(
                        text = ("${location.address}, "),
                        fontSize = 14.sp,
                        color = DarkPrimary
                    )
                    Text(
                        text = ("${location.city}, "),
                        fontSize = 14.sp,
                        color = DarkPrimary
                    )
                    Text(
                        text = location.country,
                        fontSize = 14.sp,
                        color = DarkPrimary
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    // CONTACT INFO
                    Text(
                        text = "CONTACT INFO",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextGrey
                    )
                    Text(
                        text = location.contactEmail,
                        fontSize = 14.sp,
                        color = DarkPrimary
                    )
                    Text(
                        text = location.contactPhone,
                        fontSize = 14.sp,
                        color = DarkPrimary
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
                        .clickable { onDeleteLocation() }
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
