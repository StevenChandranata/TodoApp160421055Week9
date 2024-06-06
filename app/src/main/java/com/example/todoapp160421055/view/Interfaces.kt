package com.example.todoapp160421055.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp160421055.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckedChanged(cb: CompoundButton,
                         isChecked:Boolean,
                         obj: Todo
    )
}
interface TodoEditClick {
    fun onTodoEditClick(v: View)
}
interface RadioClick {
    fun onRadioClick(v:View, priority:Int, obj:Todo)
}
interface TodoSaveChangesClick {
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}
interface ButtonAddTodoClickListener{
    fun onButtonAddTodo(v:View)
}
