package com.example.taskmanagerappmad

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<Task>>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): Task

    @Query("UPDATE task_table SET title = :title, description = :description, time = :time, date = :date WHERE id = :taskId")
    suspend fun updateTaskById(taskId: Long, title: String, description: String, time: String, date: String)
}
