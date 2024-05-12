package com.example.taskmanagerappmad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val allTasks = repository.allTasks

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

    suspend fun getTaskById(taskId: Long): Task {
        return repository.getTaskById(taskId)
    }

    fun updateTaskById(taskId: Long, title: String, description: String, time: String, date: String) {
        viewModelScope.launch {
            repository.updateTaskById(taskId, title, description, time, date)
        }
    }


}
