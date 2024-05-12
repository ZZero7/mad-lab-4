package com.example.taskmanagerappmad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class EditTask : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        val taskId = intent.getIntExtra("taskId",-1)

        val repository = TaskRepository(TaskDatabase.getDatabase(this).taskDao())
        val viewModelFactory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(this, viewModelFactory).get(TaskViewModel::class.java)

        lifecycleScope.launch {
            val upTask = taskViewModel.getTaskById(taskId.toLong())
            updateUI(upTask)
            //Log.d("UPDATE task", upTask.toString())
        }

        val updateBtn = findViewById<Button>(R.id.updateButton)

        updateBtn.setOnClickListener{

            val title = findViewById<EditText>(R.id.EditTitleInput).text.toString()
            val description = findViewById<EditText>(R.id.EditDescriptionInput).text.toString()
            val time = findViewById<EditText>(R.id.EditTimeInput).text.toString()
            val date = findViewById<EditText>(R.id.EditDateInp).text.toString()

            lifecycleScope.launch{
                taskViewModel.updateTaskById(taskId.toLong(),title,description,time,date)
            }
            startActivity(Intent(this,MainActivity::class.java))
        }


    }

    private fun updateUI(task: Task) {
        // Find views
        val titleInput = findViewById<EditText>(R.id.EditTitleInput)
        val descriptionInput = findViewById<EditText>(R.id.EditDescriptionInput)
        val timeInput = findViewById<EditText>(R.id.EditTimeInput)
        val dateInput = findViewById<EditText>(R.id.EditDateInp)


        // Set values to input fields
        titleInput.setText(task.title)
        descriptionInput.setText(task.description)
        timeInput.setText(task.time)
        dateInput.setText(task.date)
    }
    
}