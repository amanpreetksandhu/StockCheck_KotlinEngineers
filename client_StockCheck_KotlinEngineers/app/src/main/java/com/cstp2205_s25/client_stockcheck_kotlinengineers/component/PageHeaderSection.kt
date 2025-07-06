package com.cstp2205_s25.client_stockcheck_kotlinengineers.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// This component contains PAGE HEADER, SEARCH BAR AND ADD BUTTON on the page of locations and inventory
@Composable
fun PageHeaderSection(headerText: String, onNavigateToAddLocation: () -> Unit){
    Column(
        modifier = Modifier
            .padding(24.dp)
    ) {
        // Page Header
<<<<<<< HEAD
        PageHeaderText(headerText = headerText)

=======
        Text(
            text = headerText,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF289182),
            modifier = Modifier.padding(bottom = 24.dp)
        )
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
        // Search Field
        OutlinedTextField(
            value = "",
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            placeholder = { Text("Search $headerText ...", color = Color.LightGray) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                focusedLabelColor = Color.LightGray,
            )
        );
        Spacer(modifier = Modifier.padding(10.dp))
        // Add Button
        Button (
            onClick = { onNavigateToAddLocation() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(bottom = 8.dp),
<<<<<<< HEAD
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E66E5)),
=======
            colors = ButtonDefaults.buttonColors(containerColor = LightBlueAccent),
>>>>>>> 6edd97746b3aa0d959a4bc93b2a516744a715b93
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add New $headerText", color = Color.White, fontSize = 18.sp)
        }

    }
}
