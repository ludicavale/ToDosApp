package com.example.todosapp.model

data class ToDo(
    val id: Int,         // Unique identifier for the toDo
    val title: String,   // Title of the toDo
    val content: String  // Main content or body of the toDo
)