package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ArrowBackIcon(onNavigateToBackPage: () -> Unit) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Arrow back",
        tint = Color(0xFF2E66E5),
        modifier = Modifier
            .size(30.dp)
            .padding(top = 5.dp)
            .clickable { onNavigateToBackPage() }
    )
}
