package com.example.proyectofinal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectofinal.viewmodel.ContactsViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.Dp
import com.example.proyectofinal.data.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavHostController, viewModel: ContactsViewModel) {
    val contacts by viewModel.allContacts.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Contactos",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .background(Color(0xFF4CAF50), shape = RoundedCornerShape(12.dp))
                            .padding(8.dp)
                    )
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFF4CAF50), shape = CircleShape)
                            .clickable {
                                navController.navigate("contactDetail")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar()
            Spacer(modifier = Modifier.height(9.dp))
            ContactGrid(contacts = contacts, navController)
        }
    }
}

@Composable
fun SearchBar() {
    var searchQuery by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text(text = "Buscar", color = Color.White) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Buscar",
                tint = Color.White
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.Black, shape = RoundedCornerShape(10.dp))
    )
}

@Composable
fun ContactGrid(contacts: List<Contact>, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        for (i in contacts.indices step 2) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ContactItem(contact = contacts[i], navController = navController)
                if (i + 1 < contacts.size) {
                    ContactItem(contact = contacts[i + 1], navController = navController)
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ContactItem(
    contact: Contact,
    navController: NavHostController,
    width: Dp = 120.dp,
    height: Dp = 120.dp,
    circleSize: Dp = 60.dp
) {
    Box(
        modifier = Modifier
            .size(width, height)
            .padding(8.dp)
            .background(Color(0xFF4CAF50), shape = RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate("editContact/${contact.id}")
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .background(Color.Black, shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${contact.name ?: "Nombre"}\n${contact.lastName ?: "Apellido"}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}
