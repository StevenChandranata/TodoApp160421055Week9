package com.example.todoapp160421097.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp160421097.databinding.TodoItemLayoutBinding
import com.example.todoapp160421097.model.Todo

class TodoListAdapter(
    val todoList:ArrayList<Todo>,
    val adapterOnClick : (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>()
{
    class TodoViewHolder(var binding: TodoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var binding = TodoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.checkBox.setText(todoList[position].title.toString())

        holder.binding.checkBox.setOnCheckedChangeListener {
                compoundButton, b ->  adapterOnClick(todoList[position])
        }
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return todoList.size
    }
}
