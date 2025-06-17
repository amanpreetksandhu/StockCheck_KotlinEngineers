package com.cstp2205_s25.client_stockcheck_kotlinengineers.components

import android.R
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.DarkPrimary
import com.cstp2205_s25.client_stockcheck_kotlinengineers.component.TextGrey
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.Location

@Composable
fun LocationCard(
    location: Location,
    onEditLocation: () -> Unit,
    onDeleteLocation:() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        // First Row LOCATION and CONTACT NAME
        Row(
            modifier = Modifier
//                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "LOCATION",
                    color = TextGrey,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = location.name,
                    color = DarkPrimary,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(100.dp))
            Column {
                Text(
                    text = "CONTACT NAME",
                    color = TextGrey,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = location.contactName,
                    color = DarkPrimary,
                    fontSize = 16.sp
                )
            }
        }

        //Second Row ADDRESS & CONTACT INFO
        Row(
            modifier = Modifier
//                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "ADDRESS",
                    color = TextGrey,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = location.address,
                    color = DarkPrimary,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = "CONTACT INFORMATION",
                    color = TextGrey,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = location.contactEmail,
                    color = DarkPrimary,
                    fontSize = 16.sp
                )
                Text(
                    text = location.contactPhone,
                    color = DarkPrimary,
                    fontSize = 16.sp
                )

            }
        }
            // EDIT and DELETE icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

               IconButton(onClick = onDeleteLocation) {
                   Icon(Icons.Default.Delete,
                       contentDescription = "delete",
                       tint = Color.Red)
               }
                IconButton(onClick = onEditLocation) {
                    Icon(Icons.Default.Edit,
                        contentDescription = "edit",
                        tint = Color.Blue)
                }

            }

    }
}