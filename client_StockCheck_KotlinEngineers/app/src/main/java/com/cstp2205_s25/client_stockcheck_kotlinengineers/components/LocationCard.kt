package com.cstp2205_s25.client_stockcheck_kotlinengineers.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp

@Composable
fun LocationCard(){
    Card (modifier = Modifier.fillMaxWidth()){
        Row (modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            ) {
            //Left side column in card
            Column {
                Text("LOCATION")
                Text("Name")
                Spacer(modifier = Modifier.padding(16.dp))
                Text("Address")
                Text("Address Address")
                IconButton(onClick= {}) {
                    Icon(Icons.Default.Delete, contentDescription = "delete")
                }
            }
            //Spacer in between
            Spacer(modifier = Modifier.padding(30.dp))            // Right side column in Card
            Column {
                Text("CONTACT NAME")
                Text("Amanpreet Kaur")
                Spacer(modifier = Modifier.padding(16.dp))
                Text("CONTACT INFORMATION")
                Text("+1 (123) 456-7890")
                Text("aman@gmail.com")
                IconButton(onClick= {}) {
                    Icon(Icons.Default.Edit, contentDescription = "delete")
                }
            }
        }
    }
}