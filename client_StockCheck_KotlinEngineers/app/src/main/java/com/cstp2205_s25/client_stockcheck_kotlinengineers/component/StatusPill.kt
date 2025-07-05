package com.cstp2205_s25.client_stockcheck_kotlinengineers.component


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusPill(status: String) {
    val statusColor = when (status) {
        "IN STOCK" -> Color.Green
        "OUT OF STOCK" -> Color.Yellow
        else -> Color(0xFFCAD0CE)
    }
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = statusColor.copy(alpha = 0.15f), // Light background tint
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Text(
            text = status,
            color = statusColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}