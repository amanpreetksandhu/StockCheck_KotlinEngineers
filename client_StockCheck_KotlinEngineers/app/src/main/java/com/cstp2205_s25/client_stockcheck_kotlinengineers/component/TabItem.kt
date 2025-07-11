package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
){
    Surface(
        modifier = Modifier
            .clickable(onClick = onClick)
            .width(150.dp) // Fixed width for tab items
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),

        color = if (isSelected) Color(0xFF13182B) else Color(0xFF222840),

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,

                color = Color.White,
            )

        }
    }



}