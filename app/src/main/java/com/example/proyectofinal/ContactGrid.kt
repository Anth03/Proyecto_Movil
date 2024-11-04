package com.example.proyectofinal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.data.Contact

@Composable
fun ContactGrid(contacts: List<Contact>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 16.dp) // Espacio alrededor de la columna
    ) {
        val gridSize = 2
        for (i in contacts.indices step gridSize) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start // Alineación al inicio
            ) {
                ContactItem(contact = contacts[i])
                if (i + 1 < contacts.size) {
                    Spacer(modifier = Modifier.width(16.dp)) // Espacio entre elementos
                    ContactItem(contact = contacts[i + 1])
                }
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp)
            .background(Color(0xFFA5D6A7), shape = RoundedCornerShape(20.dp))
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.DarkGray, shape = RoundedCornerShape(70.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${contact.name} ${contact.lastName}",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 1.dp)
        )
    }
}
