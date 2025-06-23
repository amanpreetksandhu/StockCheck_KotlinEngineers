package com.cstp2205_s25.client_stockcheck_kotlinengineers.component.Button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedCancelButton(text: String, onClickAction: () ->Unit){
    OutlinedButton(
        onClick = { onClickAction() },
        modifier = Modifier
            .height(50.dp)
            .width(180.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Text(text, color = Color.DarkGray)
    }
}