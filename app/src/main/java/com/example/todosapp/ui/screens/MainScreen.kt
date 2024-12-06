package com.example.todosapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todosapp.model.ToDo
import com.example.todosapp.viewmodel.ProfileViewModel
import com.example.todosapp.viewmodel.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, toDoViewModel: ToDoViewModel, profileViewModel: ProfileViewModel) {
    // Observing the toDos from the ViewModel as state
    val toDos by toDoViewModel.toDos.observeAsState(emptyList())
    val userProfile by profileViewModel.userProfile.observeAsState()
    // Scaffold provides the basic structure for the screen
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TO-DO List")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                actions = {
                    IconButton(onClick = { navController.navigate("profile") }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = Color.White)
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_toDo") },
                shape = CircleShape,// Navigate to the Add ToDo screen
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add ToDo") // Icon for the FAB
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding),
        ) {

            // User Profile Information
            Text(
                text = "Name: ${userProfile?.name ?: "N/A"}",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Email: ${userProfile?.email ?: "N/A"}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        // LazyColumn to display a list of toDos
        LazyColumn(
            modifier = Modifier.padding(vertical = 4.dp), // Fill the available space
            contentPadding = innerPadding // Add padding around the content
        ) {
            // Iterate through the list of toDos and display each as a ToDoItem
            items(toDos) { toDo ->
                ToDoItem(
                    toDo = toDo,
                    onClick = {
                        navController.navigate("detail/${toDo.id}") // Navigate to the detail screen for the selected toDo
                    }
                )
            }
        }
    }
}

@Composable
fun ToDoItem(toDo: ToDo, onClick: () -> Unit) {
    // Card component for individual toDo display
    Card(
        modifier = Modifier
            .fillMaxWidth() // Card takes up the full width of the screen
            .padding(vertical = 4.dp, horizontal = 24.dp) // Vertical spacing between cards
            .clickable(onClick = onClick), // Clickable to trigger navigation or actions
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),// Elevation for shadow effect
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
    ) {
        // Column to arrange the title and content of the toDo vertically
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = toDo.title, // Display the title of the toDo
                style = MaterialTheme.typography.bodyLarge// Apply headline 6 typography
            )
            Spacer(modifier = Modifier.height(4.dp)) // Small space between title and content
            Text(
                text = toDo.content, // Display the content of the toDo
                style = MaterialTheme.typography.labelSmall // Apply body 2 typography
            )
        }
    }
}