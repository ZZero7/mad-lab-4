package com.example.taskmanagerappmad
import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun getTaskById(taskId: Long): Task {
        return taskDao.getTaskById(taskId)
    }

    suspend fun updateTaskById(taskId: Long, title: String, description: String, time: String, date: String) {
        taskDao.updateTaskById(taskId, title, description, time, date)
    }


}

