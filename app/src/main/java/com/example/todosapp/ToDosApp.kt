package com.example.todosapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todosapp.ui.screens.AddToDoScreen
import com.example.todosapp.ui.screens.ProfileScreen
import com.example.todosapp.ui.screens.DetailScreen
import com.example.todosapp.ui.screens.MainScreen
import com.example.todosapp.viewmodel.ProfileViewModel
import com.example.todosapp.viewmodel.ToDoViewModel

@Composable
fun ToDosApp() {
    val navController = rememberNavController() // Create a navigation controller for handling navigation
    val toDoViewModel: ToDoViewModel = viewModel() // Obtain an instance of the ToDoViewModel
    val profileViewModel: ProfileViewModel = viewModel()
    // Define the navigation host for the app
    NavHost(
        navController = navController, // The navigation controller used to manage navigation
        startDestination = "main" // Set the starting screen of the app
    ) {
        // Define the composable for the main screen
        composable("main") {
            MainScreen(navController, toDoViewModel, profileViewModel) // Pass the navController and ViewModel to the MainScreen
        }
        // Define the composable for the add toDo screen
        composable("add_toDo") {
            AddToDoScreen(navController, toDoViewModel) // Pass the navController and ViewModel to AddToDoScreen
        }
        // Define the composable for the detail screen with a dynamic toDo ID
        composable("detail/{toDoId}") { backStackEntry ->
            val toDoId = backStackEntry.arguments?.getString("toDoId")?.toIntOrNull() // Extract the toDo ID from the route arguments
            if (toDoId != null) {
                DetailScreen(navController, toDoViewModel, toDoId) // Pass the toDo ID to DetailScreen if it is valid
            }
        }

        composable("profile") {
            ProfileScreen(navController, profileViewModel)
        }
    }
}