package com.cstp2205_s25.client_stockcheck_kotlinengineers.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.cstp2205_s25.client_stockcheck_kotlinengineers.data.viewmodel.LocationViewModel

@Composable
fun LocationDetailsScreen(
    onNavigateToLocation: () -> Unit,
    locationViewModel: LocationViewModel
) {
    Text("Location details")
}