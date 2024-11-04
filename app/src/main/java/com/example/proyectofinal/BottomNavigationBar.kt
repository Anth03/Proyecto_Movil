package com.example.proyectofinal

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        modifier = Modifier.height(54.dp),
        containerColor = Color(0xFF333333),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Spacer(modifier = Modifier.size(0.dp)) },
            label = { Text("Añadir", color = Color.White) },
            selected = navController.currentBackStackEntry?.destination?.route == "contactDetail",
            onClick = {
                navController.navigate("contactDetail") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Spacer(modifier = Modifier.size(0.dp)) },
            label = { Text("Contactos", color = Color.White) },
            selected = navController.currentBackStackEntry?.destination?.route == "contactList",
            onClick = {
                navController.navigate("contactList") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
