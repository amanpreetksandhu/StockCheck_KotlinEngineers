package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FloatingButton(
    onNavigateToUserProfile:()-> Unit
){
    FloatingActionButton(
        containerColor = Color(0xFF1D5C88),
        contentColor = Color.White,
        onClick = { onNavigateToUserProfile() },
        ) {
    Icon(
        imageVector = Icons.Filled.Person,
        contentDescription = "UserProfile",
        tint = Color.White,
        modifier = Modifier.background(Color(0xFF1D5C88))
    )
    }
}