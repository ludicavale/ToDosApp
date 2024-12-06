package com.example.todosapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todosapp.model.ToDo

class ToDoViewModel : ViewModel() {

    // Backing property for toDos, using MutableLiveData to allow updates
    private val _toDos = MutableLiveData<MutableList<ToDo>>(mutableListOf())
    // Publicly exposed LiveData for observing the toDos list
    val toDos: LiveData<MutableList<ToDo>> = _toDos

    // Variable to keep track of the next unique ID for a new toDo
    private var nextId = 1

    // Function to add a new toDo to the list
    fun addToDo(title: String, content: String) {
        val newToDo = ToDo(
            id = nextId++,   // Assign a unique ID and increment the counter
            title = title,   // Set the title of the new toDo
            content = content // Set the content of the new toDo
        )
        _toDos.value?.add(newToDo) // Add the new toDo to the existing list
        _toDos.value = _toDos.value // Trigger observers by reassigning the value
    }

    // Function to update an existing toDo by its ID
    fun updateToDoById(id: Int, title: String, content: String) {
        _toDos.value = _toDos.value?.map { toDo ->
            // Replace the toDo with a matching ID with an updated copy
            if (toDo.id == id) toDo.copy(title = title, content = content) else toDo
        }?.toMutableList() // Convert the result back to a mutable list
    }

    // Function to delete a toDo by its ID
    fun deleteToDoById(id: Int) {
        _toDos.value = _toDos.value?.filter { it.id != id }?.toMutableList() // Remove the toDo with the matching ID
    }

    // Function to retrieve a toDo by its ID
    fun getToDoById(id: Int): ToDo? {
        return _toDos.value?.find { it.id == id } // Find the toDo with the matching ID
    }
}