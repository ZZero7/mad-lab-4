package com.example.taskmanagerappmad


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    var tasks: List<Task>,
    private val onDeleteClickListener: OnDeleteClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    interface OnDeleteClickListener {
        fun onDeleteClick(task: Task)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)

        holder.deleteButton.setOnClickListener {
            onDeleteClickListener.onDeleteClick(task)
        }

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context,EditTask::class.java).apply {
                putExtra("taskId",task.id)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        val updateButton:Button = itemView.findViewById(R.id.updateButton)
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        private val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)
        private val timeTextView: TextView = itemView.findViewById(R.id.textViewTime)
        fun bind(task: Task) {
            titleTextView.text = task.title
            descriptionTextView.text = task.description
            dateTextView.text = task.date
            timeTextView.text = task.time
        }
    }

    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
