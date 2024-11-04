package com.example.proyectofinal

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.viewmodel.ContactsViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: ContactsViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "contactList", modifier = modifier) {
        composable("contactList") {
            ContactListScreen(navController = navController, viewModel = viewModel)
        }
        composable("contactDetail") {
            ContactDetailScreen(navController = navController, viewModel = viewModel)
        }
        composable("editContact/{contactId}") { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")?.toIntOrNull()
            contactId?.let { id ->
                val contact = viewModel.getContactById(id).observeAsState().value
                contact?.let {
                    ContactEditScreen(navController = navController, viewModel = viewModel, contact = it)
                }
            }
        }
    }
}
