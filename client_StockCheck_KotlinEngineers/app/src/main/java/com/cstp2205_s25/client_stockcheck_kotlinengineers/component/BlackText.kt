package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun BlackText(text: String){
    Text(
        text = text,
        fontSize = 14.sp,
        color = DarkPrimary
    )
}