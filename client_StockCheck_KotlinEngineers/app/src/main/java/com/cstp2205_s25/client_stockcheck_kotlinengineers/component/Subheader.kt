package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun Subheader(text: String){
    Text(
        text =text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF232940),
        modifier = Modifier.padding(vertical = 12.dp)
    )
}