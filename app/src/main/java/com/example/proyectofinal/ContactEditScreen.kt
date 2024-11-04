package com.example.proyectofinal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.proyectofinal.data.Contact
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactEditScreen(navController: NavHostController, viewModel: ContactsViewModel, contact: Contact) {
    var name by remember { mutableStateOf(contact.name ?: "") }
    var lastName by remember { mutableStateOf(contact.lastName ?: "") }
    var phoneNumber by remember { mutableStateOf(contact.phoneNumber ?: "") }
    var email by remember { mutableStateOf(contact.email ?: "") }
    var nickname by remember { mutableStateOf(contact.nickname ?: "") }
    var address by remember { mutableStateOf(contact.address ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Contacto", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                actions = {
                    Text(
                        text = "Guardar",
                        color = Color(0xff649562),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                val updatedContact = contact.copy(
                                    name = name,
                                    lastName = lastName,
                                    phoneNumber = phoneNumber,
                                    email = email,
                                    nickname = nickname,
                                    address = address
                                )
                                viewModel.viewModelScope.launch(Dispatchers.IO) {
                                    viewModel.update(updatedContact)
                                    withContext(Dispatchers.Main) {
                                        navController.navigateUp()
                                    }
                                }
                            },
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Text(
                        text = "Cancelar",
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navController.navigateUp() },
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sección de Foto
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .clickable { /* Funcionalidad para agregar foto */ },
                contentAlignment = Alignment.Center
            ) {
                Text("Foto", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Campos de texto para el contacto
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 4.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Apellido") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 4.dp)
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Teléfono") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 4.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 4.dp)
            )
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("Apodo") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 4.dp)
            )
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Dirección") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Botón para eliminar el contacto
            Button(
                onClick = {
                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                        viewModel.delete(contact)
                        withContext(Dispatchers.Main) {
                            navController.navigateUp()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Eliminar", color = Color.White)
            }
        }
    }
}
