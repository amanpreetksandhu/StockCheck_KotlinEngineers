package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities.Location
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun CircledEditIcon(
//    location: Location,
    onEditLocation: () -> Unit,
    locationViewModel: LocationViewModel,
) {
    Icon(
        imageVector = Icons.Default.Edit,
        contentDescription = "Edit",
        tint = Color.White,
        modifier = Modifier
            .size(24.dp)
            .background(Color(0xFF2E66E5))
//            .clickable {
//                locationViewModel.locationState.value = location
//                onEditLocation()
//            }
    )
}